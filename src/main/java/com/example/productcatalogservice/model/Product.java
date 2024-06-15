package com.example.productcatalogservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.engine.internal.Cascade;

@Getter
@Setter
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product extends BaseModel {

    //name, description, cost, seller id, category, rating, review id, imageUrl, isPrimeEligible

    private String name;

    private Double cost;

    private String description;
    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

    private String imageUrl;

    //JsonManagedReference is added here and JsonBackReference will be added in Category
//to make sure, when we will make call from Postman for getProduct(2), it will get all
//values of product from product table and then it goes to category table and again try
//to get value from product table , hence converting into infinite loop.
//So this managedreference and backreference helps in solving that problem
//Source - https://www.baeldung.com/jackson-annotations
}
