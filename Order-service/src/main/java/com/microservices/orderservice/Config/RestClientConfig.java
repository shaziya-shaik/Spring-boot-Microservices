package com.microservices.orderservice.Config;

import com.microservices.orderservice.Client.InventoryClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.http.client.ClientHttpRequestFactorySettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.time.Duration;

//@Configuration
public class RestClientConfig {
//
//    @Value("${inventory.url}")
//    private String inventoryServiceUrl;
//    @Bean
//    public InventoryClient inventoryClient() {
//        RestClient restClient=RestClient.builder()
//                .baseUrl(inventoryServiceUrl)
//                .build();
//        var restClientAdapter= RestClientAdapter.create(restClient);
//        var httpServiceProxyFactory= HttpServiceProxyFactory.builderFor(restClientAdapter).build();
//        return httpServiceProxyFactory.createClient(InventoryClient.class);
//
//    }


}
