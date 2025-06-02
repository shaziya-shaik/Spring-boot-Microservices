package com.microservices.orderservice.Client;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

@FeignClient(value = "inventory-service" , url = "${inventory.url}")
public interface InventoryClient {
    Logger log = LoggerFactory.getLogger(InventoryClient.class);


    @RequestMapping(method = RequestMethod.GET , value = "/api/inventory")
    boolean isInStock(@RequestParam("skuCode") String skuCode, @RequestParam("quantity") Integer quantity);


    default boolean fallbackMethod(String code ,Integer quantity, Throwable throwable) {
        log.info("Cannot get inventory for skuCode {}, failure reason:{}", code, throwable.getMessage());
        return false;
    }


}
