package com.example.productcatalogservice.controller;

import com.example.productcatalogservice.dtos.ProductDto;
import com.example.productcatalogservice.model.Category;
import com.example.productcatalogservice.model.Product;
import com.example.productcatalogservice.services.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    //@Qualifier is alternative of @Primary. Whatever name you mention in this qualifier, it will be picked up
    @Autowired
    private ProductServiceInterface ProductServiceInterface;

    @GetMapping
    public List<Product> getProducts() {
        return ProductServiceInterface.getProducts();
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long productId) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("called by", "smart frontend");
        try {
            if(productId < 1) {
                headers.add("called by", "invalid frontend");
                throw new IllegalArgumentException("Id is invalid");
            }
            Product product = ProductServiceInterface.getProduct(productId);
            return new ResponseEntity<>(product, headers, HttpStatus.OK);
        } catch(Exception ex) {
            throw ex;
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product createProduct(@RequestBody ProductDto productDto) {
        return ProductServiceInterface.createProduct(mapProduct(productDto));
    }

    @PutMapping("{id}")
    public Product replaceProduct(@PathVariable("id") Long id,@RequestBody ProductDto productDto) {
        return ProductServiceInterface.replaceProduct(id, mapProduct(productDto));
    }

    @DeleteMapping("{id}")
    public Product deleteProduct(@PathVariable("id") Long productId) {
        return ProductServiceInterface.deleteProduct(productId);
    }


    private Product mapProduct(ProductDto productDto) {

        Product product = new Product();
        Category category = new Category();
        if(productDto.getCategoryDto() != null) {
            category.setId(productDto.getCategoryDto().getId());
            category.setName(productDto.getCategoryDto().getName());
        }
        product.setCategory(category);
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setCost(productDto.getCost());
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrl());
        return product;
    }
}
