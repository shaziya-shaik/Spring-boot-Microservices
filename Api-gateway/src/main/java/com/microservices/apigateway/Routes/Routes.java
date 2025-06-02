package com.microservices.apigateway.Routes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;

@Configuration
public class Routes {
    @Value("${product-service-url}")
    private String productServiceUrl;
    @Value("${order-service-url}")
    private String orderServiceUrl;
    @Value("${inventory-service-url}")
    private String inventoryServiceUrl;
    @Value("${notification-service-url}")
    private String notificationServiceUrl;



    @Bean
    public RouterFunction<ServerResponse> ProductServiceRoutes(CircuitBreakerFilterFunctions.FilterSupplier circuitBreakerFilterFunctionsSupplier) {
        return route("product-service")
                .route(RequestPredicates.path("/api/product"), HandlerFunctions.http(productServiceUrl))
               // .filter(CircuitBreakerFilterFunctions.circuitBreaker("ProductServiceCircuitBreaker" ,
                  //      URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> OrderServiceRoutes() {
        return route("order-service")
                .route(RequestPredicates.path("/api/order"), HandlerFunctions.http(orderServiceUrl))
//                .filter(CircuitBreakerFilterFunctions.circuitBreaker("OrderServiceCircuitBreaker" ,
//                        URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> InventoryServiceRoutes() {
        return route("inventory-service")
                .route(RequestPredicates.path("/api/inventory"), HandlerFunctions.http(notificationServiceUrl))
//                .filter(CircuitBreakerFilterFunctions.circuitBreaker("InventoryServiceCircuitBreaker" ,
//                        URI.create("forward:/fallbackRoute")))
                .build();
    }

//    @Bean
//    public RouterFunction<ServerResponse> ProductServiceSwaggerRoutes() {
//        return route("product-service-swagger")
//                .route(RequestPredicates.path("/aggregate/product-service/v3/api-docs"), HandlerFunctions.http("http://localhost:8081"))
//                .filter(CircuitBreakerFilterFunctions.circuitBreaker("ProductServiceSwaggerCircuitBreaker" ,
//                        URI.create("forward:/fallbackRoute")))
//                .filter(setPath("api-docs"))
//                .build();
//    }
//
//    @Bean
//    public RouterFunction<ServerResponse> OrderServiceSwaggerRoutes() {
//        return route("order-service-swagger")
//                .route(RequestPredicates.path("/aggregate/order-service/v3/api-docs"), HandlerFunctions.http("http://localhost:8082"))
//                .filter(CircuitBreakerFilterFunctions.circuitBreaker("OrderServiceSwaggerCircuitBreaker" ,
//                        URI.create("forward:/fallbackRoute")))
//                .filter(setPath("api-docs"))
//                .build();
//    }
//
//    @Bean
//    public RouterFunction<ServerResponse> InventoryServiceSwaggerRoutes() {
//        return route("inventory-service-swagger")
//                .route(RequestPredicates.path("/aggregate/inventory-service/v3/api-docs"), HandlerFunctions.http("http://localhost:8083"))
//                .filter(CircuitBreakerFilterFunctions.circuitBreaker("iNVENTORYServiceSwaggerCircuitBreaker" ,
//                URI.create("forward:/fallbackRoute")))
//                .filter(setPath("api-docs"))
//                .build();
//    }
//
//    @Bean
//    public RouterFunction<ServerResponse> fallbackRoutes() {
//        return route("fallbackRoute")
//                .GET("/fallbackRoute",
//                        request -> ServerResponse
//                                .status(HttpStatus.SERVICE_UNAVAILABLE)
//                                .body("Service unavailable. Please try again later."))
//                .build();
//    }

}
