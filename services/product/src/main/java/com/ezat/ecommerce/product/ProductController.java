package com.ezat.ecommerce.product;

import com.ezat.ecommerce.exception.ProductPurchaseException;
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
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name = "Product API", description = "Operations for managing products")
public class ProductController {
    private final ProductService service;

    // create product
    @PostMapping
    @Operation(summary = "Create a new product", description = "Creates a new product in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product created successfully", 
                    content = @Content(schema = @Schema(implementation = Integer.class))),
            @ApiResponse(responseCode = "400", description = "Invalid product data supplied")
    })
    public ResponseEntity<Integer> createProduct(
            @RequestBody @Valid ProductRequest product) {
        return ResponseEntity.ok(service.createProduct(product));
    }

    // purchaseProducts
    @PostMapping("/purchase")
    @Operation(summary = "Purchase products", description = "Process a product purchase request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products purchased successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid purchase request"),
            @ApiResponse(responseCode = "404", description = "Products not found")
    })
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(
            @RequestBody List<ProductPurchaseRequest> request
    ) throws ProductPurchaseException {
        return ResponseEntity.ok(service.purchaseProducts(request));
    }

    // findById
    @GetMapping("/{product-id}")
    @Operation(summary = "Find product by ID", description = "Returns a single product by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product found"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<ProductResponse> findById(@PathVariable("product-id") Integer productId) {
        return ResponseEntity.ok(service.findById(productId));
    }
    
    // findAll
    @GetMapping
    @Operation(summary = "Find all products", description = "Returns a list of all products")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    public ResponseEntity<List<ProductResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
