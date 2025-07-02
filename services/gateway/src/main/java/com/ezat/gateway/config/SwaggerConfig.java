package com.ezat.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public RouteLocator swaggerRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                // Product Service Swagger routes
                .route("product-service-swagger", r -> r
                        .path("/product-service/swagger-ui/**", "/product-service/swagger-ui.html")
                        .filters(f -> f.rewritePath("/product-service/swagger-ui/(?<segment>.*)", "/swagger-ui/$1")
                                    .rewritePath("/product-service/swagger-ui.html", "/swagger-ui.html"))
                        .uri("lb://PRODUCT-SERVICE"))
                .route("product-service-api-docs", r -> r
                        .path("/product-service/v3/api-docs/**", "/product-service/api-docs/**")
                        .filters(f -> f.rewritePath("/product-service/v3/api-docs/(?<segment>.*)", "/v3/api-docs/$1")
                                    .rewritePath("/product-service/api-docs/(?<segment>.*)", "/api-docs/$1"))
                        .uri("lb://PRODUCT-SERVICE"))
                
                // Customer Service Swagger routes
                .route("customer-service-swagger", r -> r
                        .path("/customer-service/swagger-ui/**", "/customer-service/swagger-ui.html")
                        .filters(f -> f.rewritePath("/customer-service/swagger-ui/(?<segment>.*)", "/swagger-ui/$1")
                                    .rewritePath("/customer-service/swagger-ui.html", "/swagger-ui.html"))
                        .uri("lb://CUSTOMER-SERVICE"))
                .route("customer-service-api-docs", r -> r
                        .path("/customer-service/v3/api-docs/**", "/customer-service/api-docs/**")
                        .filters(f -> f.rewritePath("/customer-service/v3/api-docs/(?<segment>.*)", "/v3/api-docs/$1")
                                    .rewritePath("/customer-service/api-docs/(?<segment>.*)", "/api-docs/$1"))
                        .uri("lb://CUSTOMER-SERVICE"))
                
                // Order Service Swagger routes
                .route("order-service-swagger", r -> r
                        .path("/order-service/swagger-ui/**", "/order-service/swagger-ui.html")
                        .filters(f -> f.rewritePath("/order-service/swagger-ui/(?<segment>.*)", "/swagger-ui/$1")
                                    .rewritePath("/order-service/swagger-ui.html", "/swagger-ui.html"))
                        .uri("lb://ORDER-SERVICE"))
                .route("order-service-api-docs", r -> r
                        .path("/order-service/v3/api-docs/**", "/order-service/api-docs/**")
                        .filters(f -> f.rewritePath("/order-service/v3/api-docs/(?<segment>.*)", "/v3/api-docs/$1")
                                    .rewritePath("/order-service/api-docs/(?<segment>.*)", "/api-docs/$1"))
                        .uri("lb://ORDER-SERVICE"))
                
                // Payment Service Swagger routes
                .route("payment-service-swagger", r -> r
                        .path("/payment-service/swagger-ui/**", "/payment-service/swagger-ui.html")
                        .filters(f -> f.rewritePath("/payment-service/swagger-ui/(?<segment>.*)", "/swagger-ui/$1")
                                    .rewritePath("/payment-service/swagger-ui.html", "/swagger-ui.html"))
                        .uri("lb://PAYMENT-SERVICE"))
                .route("payment-service-api-docs", r -> r
                        .path("/payment-service/v3/api-docs/**", "/payment-service/api-docs/**")
                        .filters(f -> f.rewritePath("/payment-service/v3/api-docs/(?<segment>.*)", "/v3/api-docs/$1")
                                    .rewritePath("/payment-service/api-docs/(?<segment>.*)", "/api-docs/$1"))
                        .uri("lb://PAYMENT-SERVICE"))
                
                .build();
    }
} 