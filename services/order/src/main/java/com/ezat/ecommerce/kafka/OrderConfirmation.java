package com.ezat.ecommerce.kafka;

import com.ezat.ecommerce.customer.CustomerResponse;
import com.ezat.ecommerce.order.PaymentMethod;
import com.ezat.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
