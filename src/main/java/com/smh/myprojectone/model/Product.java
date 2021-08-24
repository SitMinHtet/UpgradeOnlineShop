package com.smh.myprojectone.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private int quantity;
    private double price;
    private String description;
    private String imageName;

    @ManyToOne
    private Category category;

}
