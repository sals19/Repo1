package com.example.productcatalogservice.TableInheritance.TablePerClass;

import jakarta.persistence.Entity;

@Entity(name = "tpc_instructor")
public class Instructor extends User {

    private String company;

    private Double rating;
}
