package com.ezat.ecommerce.order;

import org.springframework.stereotype.Service;

@Service
public class OrderMapper {
    public OrderResponse toOrderResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId()
        );
    }

    public Order toOrder(OrderRequest orderRequest) {
        if(orderRequest == null) {
            return null;
        }
        return Order.builder()
                .id(orderRequest.id())
                .reference(orderRequest.reference())
                .paymentMethod(orderRequest.paymentMethod())
                .customerId(orderRequest.customerId())
                .build();

    }
}
