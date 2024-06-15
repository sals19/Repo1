package com.example.productcatalogservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {

    private Long id;

    private String name;

    private String description;

    private Double cost;

    private CategoryDto categoryDto;

    private String imageUrl;
}
