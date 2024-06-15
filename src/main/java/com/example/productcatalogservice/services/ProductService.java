package com.example.productcatalogservice.services;

import com.example.productcatalogservice.Clients.FakeStoreClient;
import com.example.productcatalogservice.dtos.FakeStoreProductDto;
import com.example.productcatalogservice.model.Category;
import com.example.productcatalogservice.model.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService implements ProductServiceInterface {

    private final RestTemplateBuilder restTemplateBuilder;

    private final FakeStoreClient fakeStoreClient;


    public ProductService(RestTemplateBuilder rtb, FakeStoreClient fsc) {
        restTemplateBuilder = rtb;
        fakeStoreClient = fsc;
    }
    @Override
    public Product getProduct(Long id) {
        if(id <=0 ){
            throw new IllegalArgumentException("Invalid Id");
        }
        FakeStoreProductDto fakeStoreProductDto = fakeStoreClient.getProduct(id);
        return mapProduct(fakeStoreProductDto);
    }

    @Override
    public List<Product> getProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto[] fakestoreProductDto = restTemplate.getForEntity("https://fakestoreapi.com/products/", FakeStoreProductDto[].class).getBody();
        List<Product> products = new ArrayList<>();
        assert fakestoreProductDto != null;
        for(FakeStoreProductDto fakeStoreDto : fakestoreProductDto)
            products.add(mapProduct(fakeStoreDto));
        return products;
    }

    @Override
    public Product createProduct(Product product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto fakeStoreDtoReq = getFakeStoreDto(product);
        FakeStoreProductDto fakeStoreProductDtoRes = restTemplate.postForEntity("https://fakestoreapi.com/products", fakeStoreDtoReq, FakeStoreProductDto.class).getBody();
        assert fakeStoreProductDtoRes != null;
        return mapProduct(fakeStoreProductDtoRes);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto fakeStoreDtoReq = getFakeStoreDto(product);
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoRes = putForEntity("https://fakestoreapi.com/products/{id}", fakeStoreDtoReq, FakeStoreProductDto.class, id);
        return mapProduct(Objects.requireNonNull(fakeStoreProductDtoRes.getBody()));

    }

    @Override
    public Product deleteProduct(Long productId) {

        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntityRes = deleteForEntity("https://fakestoreapi.com/products/{productId}", null, FakeStoreProductDto.class, productId);
        return mapProduct(Objects.requireNonNull(fakeStoreProductDtoResponseEntityRes.getBody()));
    }

    private ResponseEntity<FakeStoreProductDto> deleteForEntity(String url, @Nullable Object request, Class<FakeStoreProductDto> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, HttpMethod.DELETE, requestCallback, responseExtractor, uriVariables);
    }

    private ResponseEntity<FakeStoreProductDto> putForEntity(String url, @Nullable Object request, Class<FakeStoreProductDto> responseType, Object... uriVariables)
            throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, HttpMethod.PUT, requestCallback, responseExtractor, uriVariables);
    }

    private Product mapProduct(FakeStoreProductDto fakeStoreProductDto) {

        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setCost(fakeStoreProductDto.getPrice());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImageUrl(fakeStoreProductDto.getImageUrl());
        product.setName(fakeStoreProductDto.getTitle());
        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }

    private FakeStoreProductDto getFakeStoreDto(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImageUrl(product.getImageUrl());
        fakeStoreProductDto.setTitle(product.getName());
        fakeStoreProductDto.setPrice(product.getCost());
        if(product.getCategory()!=null)
            fakeStoreProductDto.setCategory(product.getCategory().getName());
        return fakeStoreProductDto;
    }
}
