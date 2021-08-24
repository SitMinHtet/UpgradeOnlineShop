package com.smh.myprojectone.service;

import com.smh.myprojectone.model.Category;
import com.smh.myprojectone.model.Product;

import java.util.List;

public interface ProductService {

    Product create(Product product);
    Product findById(int id);
    List<Product> findAll();
    void deleteProductById(int id);
    //void updateProductById(int id,Product product);
    List<Product> productByCategoryId(int id);
}
