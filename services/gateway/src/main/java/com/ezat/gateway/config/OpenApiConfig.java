package com.ezat.gateway.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    public OpenAPI gatewayOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("E-Commerce Microservices API Gateway")
                        .description("API Gateway Documentation with links to all microservices:<br><br>" +
                                "<ul>" +
                                "<li><a href='/product-service/swagger-ui.html'>Product Service API</a></li>" +
                                "<li><a href='/customer-service/swagger-ui.html'>Customer Service API</a></li>" +
                                "<li><a href='/order-service/swagger-ui.html'>Order Service API</a></li>" +
                                "<li><a href='/payment-service/swagger-ui.html'>Payment Service API</a></li>" +
                                "</ul>")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Ezat")
                                .email("support@example.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html")));
    }
} 