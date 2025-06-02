package com.microservices.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class OrderResponse {
    private boolean success;
    private String message;

    public OrderResponse(boolean b, String orderPlacedSuccessfully) {
    }
}
