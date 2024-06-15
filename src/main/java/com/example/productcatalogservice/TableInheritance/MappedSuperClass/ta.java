package com.example.productcatalogservice.TableInheritance.MappedSuperClass;

import jakarta.persistence.Entity;

@Entity(name = "msc_ta")
public class ta extends User {

    private Double rating;

    private Boolean isFullTime;
}
