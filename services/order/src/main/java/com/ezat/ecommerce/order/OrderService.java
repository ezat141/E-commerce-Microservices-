package com.ezat.ecommerce.order;

import com.ezat.ecommerce.customer.CustomerClient;
import com.ezat.ecommerce.exception.BusinessException;
import com.ezat.ecommerce.kafka.OrderConfirmation;
import com.ezat.ecommerce.kafka.OrderProducer;
import com.ezat.ecommerce.orderline.OrderLineRequest;
import com.ezat.ecommerce.orderline.OrderLineService;
import com.ezat.ecommerce.payment.PaymentClient;
import com.ezat.ecommerce.payment.PaymentRequest;
import com.ezat.ecommerce.product.ProductClient;
import com.ezat.ecommerce.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper mapper;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final PaymentClient paymentClient;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;

    @Transactional
    public Integer createOrder(OrderRequest orderRequest) {
        var customer = customerClient.findCustomerById(orderRequest.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No customer exists with the provided ID"));

        // purchase the products --> product-ms (RestTemplate)
        var purchasedProducts = productClient.purchaseProducts(orderRequest.products());

        // persist the order
        var order = orderRepository.save(mapper.toOrder(orderRequest));

        // persist order lines
        for(PurchaseRequest purchaseRequest: orderRequest.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );

        }



        // start payment process --> payment-ms (RestTemplate)

        var paymentRequest = new PaymentRequest(
                orderRequest.amount(),
                orderRequest.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );

        paymentClient.requestOrderPayment(paymentRequest);

        // send the order confirmation email --> notification-ms (kafka)

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        orderRequest.reference(),
                        orderRequest.amount(),
                        orderRequest.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );
        return order.getId();
    }

    public OrderResponse findOrderById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(mapper::toOrderResponse)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No order found with the provided ID: %d", id)));
    }

    public List<OrderResponse> findAllOrders() {
        return orderRepository.findAll().stream()
                .map(mapper::toOrderResponse)
                .collect(Collectors.toList());
    }

}
