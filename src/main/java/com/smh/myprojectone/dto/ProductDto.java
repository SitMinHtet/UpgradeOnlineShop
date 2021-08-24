package com.smh.myprojectone.dto;


import lombok.Data;


@Data
public class ProductDto {

    private Integer id;
    private String name;
    private int quantity;
    private Double price;
    private String description;
    private String imageName;
    private int categoryId;
}
