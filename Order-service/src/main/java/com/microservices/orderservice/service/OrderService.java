package com.microservices.orderservice.service;

import com.microservices.orderservice.Client.InventoryClient;
import com.microservices.orderservice.dto.OrderRequest;
import com.microservices.orderservice.dto.OrderResponse;
import com.microservices.orderservice.event.OrderPlacedEvent;
import com.microservices.orderservice.model.Order;
import com.microservices.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;



    public void placeOrder(OrderRequest orderRequest) {
        log.info("Calling Inventory service with skuCode = {}, quantity = {}", orderRequest.skuCode(), orderRequest.quantity());
        var isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());


        if (isProductInStock) {
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.price().multiply(BigDecimal.valueOf(orderRequest.quantity())));
            order.setSkuCode(orderRequest.skuCode());
            order.setQuantity(orderRequest.quantity());


            orderRepository.save(order);
            //send message to kafka topic
            OrderPlacedEvent   orderPlacedEvent = new OrderPlacedEvent(order.getOrderNumber(),orderRequest.userDetails().email());
            log.info("Start- sending OrderPlaceEvent{} to kafka topic order-palced");
            kafkaTemplate.send("order-placed", orderPlacedEvent);
            log.info("end- sending OrderPlaceEvent{} to kafka topic order-palced");


        } else {
            throw new RuntimeException("Product with stock code " + orderRequest.skuCode() + " is not in stock");
        }
    }
}
