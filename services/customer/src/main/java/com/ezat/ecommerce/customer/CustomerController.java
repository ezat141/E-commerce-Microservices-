package com.ezat.ecommerce.customer;

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
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
@Tag(name = "Customer API", description = "Operations for managing customers")
public class CustomerController {

    private final CustomerService service;

    // Add your endpoints here

    @PostMapping
    @Operation(summary = "Create a new customer", description = "Creates a new customer in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer created successfully", 
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Invalid customer data supplied")
    })
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(service.createCustomer(request));
    }

    @PutMapping
    @Operation(summary = "Update an existing customer", description = "Updates customer information in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Customer updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid customer data supplied"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    public ResponseEntity<Void> updateCustomer(@RequestBody @Valid CustomerRequest request) {
        this.service.updateCustomer(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    @Operation(summary = "Find all customers", description = "Returns a list of all customers")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    public ResponseEntity<List<CustomerResponse>> findAll() {
        return ResponseEntity.ok(service.findAllCustomers());
    }

    @GetMapping("/exists/{customer-id}")
    @Operation(summary = "Check if customer exists", description = "Checks if a customer with the given ID exists")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation")
    })
    public ResponseEntity<Boolean> existsById(@PathVariable("customer-id") String customerId) {
        return ResponseEntity.ok(service.existsById(customerId));
    }

    @GetMapping("/{customer-id}")
    @Operation(summary = "Find customer by ID", description = "Returns a single customer by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer found"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    public ResponseEntity<CustomerResponse> findById(@PathVariable("customer-id") String customerId) {
        return ResponseEntity.ok(service.findById(customerId));
    }

    @DeleteMapping("/{customer-id}")
    @Operation(summary = "Delete a customer", description = "Deletes a customer by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Customer deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    public ResponseEntity<Void> deleteById(@PathVariable("customer-id") String customerId) {
        this.service.deleteCustomer(customerId);
        return ResponseEntity.accepted().build();
    }



}
