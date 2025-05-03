package com.ezat.ecommerce.order;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;


    // craete order
    @PostMapping
    public ResponseEntity<Integer> createOrder( @RequestBody @Valid OrderRequest orderRequest) {
        return ResponseEntity.ok(service.createOrder(orderRequest));
    }


    // findAll
    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll() {
        return ResponseEntity.ok(service.findAllOrders());
    }

    // findById
    @GetMapping("/{order-id}")
    public ResponseEntity<OrderResponse> findById(@PathVariable("order-id") Integer orderId) {
        return ResponseEntity.ok(service.findOrderById(orderId));
    }


}
