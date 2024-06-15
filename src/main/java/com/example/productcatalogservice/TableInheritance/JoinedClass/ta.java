package com.example.productcatalogservice.TableInheritance.JoinedClass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name="jc_ta")
@PrimaryKeyJoinColumn(name = "userId")
public class ta extends User {

    private Double rating;

    private Boolean isFullTime;
}
