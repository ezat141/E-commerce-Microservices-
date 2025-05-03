package com.ezat.ecommerce.orderline;

import com.ezat.ecommerce.order.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(
                orderLine.getId(),
                orderLine.getQuantity()
        );
    }

    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {

        return OrderLine.builder()
                .id(orderLineRequest.id())
                .productId(orderLineRequest.productId())
                .order(
                        Order.builder()
                                .id(orderLineRequest.orderId())
                                .build()
                )
                .quantity(orderLineRequest.quantity())
                .build();

    }
}
