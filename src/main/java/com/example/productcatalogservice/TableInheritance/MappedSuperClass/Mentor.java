package com.example.productcatalogservice.TableInheritance.MappedSuperClass;

import jakarta.persistence.Entity;

@Entity(name = "msc_mentor")
public class Mentor extends User {

    private Integer noOfHours;

    private Boolean isActive;
}
