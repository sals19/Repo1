package com.example.productcatalogservice.Clients;

import com.example.productcatalogservice.dtos.FakeStoreProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FakeStoreClient {

    RestTemplateBuilder restTemplateBuilder;

    public FakeStoreClient(RestTemplateBuilder rtb) {
        restTemplateBuilder = rtb;
    }

    public FakeStoreProductDto getProduct(Long productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        return restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreProductDto.class, productId).getBody();
    }
}
