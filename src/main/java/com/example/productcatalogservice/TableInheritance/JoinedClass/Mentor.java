package com.example.productcatalogservice.TableInheritance.JoinedClass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name="jc_mentor")
@PrimaryKeyJoinColumn(name = "userId")
public class Mentor extends User {

    private Integer noOfHours;

    private Boolean isActive;
}
