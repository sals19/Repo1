package com.example.productcatalogservice.services;

import com.example.productcatalogservice.model.Product;
import com.example.productcatalogservice.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Primary
@Service
public class StorageProductService implements ProductServiceInterface {

    private final ProductRepo productRepo;

    public StorageProductService(ProductRepo productrepo) {
        this.productRepo = productrepo;
    }

    @Override
    public Product getProduct(Long productId) {
        Optional<Product> product = productRepo.findProductById(productId);
        return product.orElse(null);
    }

    @Override
    public List<Product> getProducts() {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product deleteProduct(Long productId) {
        return null;
    }
}
