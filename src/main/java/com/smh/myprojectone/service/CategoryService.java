package com.smh.myprojectone.service;

import com.smh.myprojectone.model.Category;

import java.util.List;

public interface CategoryService {

    Category create(Category category);
    Category findById(int id);
    List<Category> findAll();
    void deleteCategoryById(int id);


}
