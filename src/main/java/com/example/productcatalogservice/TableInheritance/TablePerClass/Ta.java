package com.example.productcatalogservice.TableInheritance.TablePerClass;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity(name="tpc_ta")
public class Ta extends User {

    private Double rating;

    private Boolean isFullTime;
}
