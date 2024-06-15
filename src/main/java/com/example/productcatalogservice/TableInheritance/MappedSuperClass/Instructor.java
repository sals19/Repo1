package com.example.productcatalogservice.TableInheritance.MappedSuperClass;

import jakarta.persistence.Entity;

@Entity(name = "msc_instructor")
public class Instructor extends User {

    private String company;

    private Double rating;
}
