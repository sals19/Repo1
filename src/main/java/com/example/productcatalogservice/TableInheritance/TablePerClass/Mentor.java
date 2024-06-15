package com.example.productcatalogservice.TableInheritance.TablePerClass;

import jakarta.persistence.Entity;

@Entity(name = "tpc_mentor")
public class Mentor extends User {

    private Integer noOfHours;

    private Boolean isActive;
}
