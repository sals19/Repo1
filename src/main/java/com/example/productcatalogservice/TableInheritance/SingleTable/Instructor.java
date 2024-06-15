package com.example.productcatalogservice.TableInheritance.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;

@Entity(name="st_instructor")
@DiscriminatorValue(value = "3")
public class Instructor extends User {

    private String company;

    private Double rating;
}
