package com.example.productcatalogservice.TableInheritance.JoinedClass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name="jc_instructor")
@PrimaryKeyJoinColumn(name = "userId")
public class Instructor extends User {

    private String company;

    private Double rating;
}
