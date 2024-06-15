package com.example.productcatalogservice.services;

import com.example.productcatalogservice.model.Product;

import java.util.List;

public interface ProductServiceInterface {

    Product getProduct(Long productId);

    List<Product> getProducts();

    Product createProduct(Product product);

    Product replaceProduct(Long id, Product product);

    Product deleteProduct(Long productId);

}
