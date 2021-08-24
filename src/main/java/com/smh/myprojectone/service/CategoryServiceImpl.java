package com.smh.myprojectone.service;

import com.smh.myprojectone.model.Category;
import com.smh.myprojectone.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{


    private final CategoryRepository categoryRepo;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepo = categoryRepository;
    }

    @Override
    public Category create(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public Category findById(int id) {
        return categoryRepo.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Id is not found"));
    }

    @Override
    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    public void deleteCategoryById(int id) {
        categoryRepo.deleteById(id);
    }


}
