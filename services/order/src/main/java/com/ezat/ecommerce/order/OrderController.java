package com.ezat.ecommerce.order;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Tag(name = "Order API", description = "Operations for managing orders")
public class OrderController {
    private final OrderService service;

    // create order
    @PostMapping
    @Operation(summary = "Create a new order", description = "Creates a new order in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order created successfully", 
                    content = @Content(schema = @Schema(implementation = Integer.class))),
            @ApiResponse(responseCode = "400", description = "Invalid order data supplied")
    })
    public ResponseEntity<Integer> createOrder(@RequestBody @Valid OrderRequest orderRequest) {
        return ResponseEntity.ok(service.createOrder(orderRequest));
    }

    // findAll
    @GetMapping
    @Operation(summary = "Find all orders", description = "Returns a list of all orders")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    public ResponseEntity<List<OrderResponse>> findAll() {
        return ResponseEntity.ok(service.findAllOrders());
    }

    // findById
    @GetMapping("/{order-id}")
    @Operation(summary = "Find order by ID", description = "Returns a single order by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order found"),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    public ResponseEntity<OrderResponse> findById(@PathVariable("order-id") Integer orderId) {
        return ResponseEntity.ok(service.findOrderById(orderId));
    }
}
