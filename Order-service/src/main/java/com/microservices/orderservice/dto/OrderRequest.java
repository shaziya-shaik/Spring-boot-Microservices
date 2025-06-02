package com.microservices.orderservice.dto;

import io.swagger.v3.oas.annotations.info.Contact;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record OrderRequest( String skuCode,  BigDecimal price, Integer quantity, UserDetails userDetails) {
    public record UserDetails(String email, String firstName, String lastName) {
    }
}
