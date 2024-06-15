package com.example.productcatalogservice.repositories;

import com.example.productcatalogservice.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    Product save(Product product);

    Optional<Product> findProductById(Long id);

//    List<Product> getProductsInPriceRange(Double low, Double high);

//    List<Product> getProducts();

//    @Query("select p.name from Product p where p.id = :prodId")
//    String getProductNamebyId(@Param("prodId") Long productId);

//    @Query("select c.name from Product p join Category c on p.id = c.id and p.name = ?1")
//    String getCategoryNamefromProductId(Long id);

//    Page<Product> findByNameEquals(String query, Pageable pageable);

}
