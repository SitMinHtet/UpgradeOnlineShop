package com.smh.myprojectone.repository;

import com.smh.myprojectone.model.Category;
import com.smh.myprojectone.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
