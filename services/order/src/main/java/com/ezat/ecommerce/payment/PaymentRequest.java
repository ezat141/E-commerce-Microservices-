package com.ezat.ecommerce.payment;

import com.ezat.ecommerce.customer.CustomerResponse;
import com.ezat.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
