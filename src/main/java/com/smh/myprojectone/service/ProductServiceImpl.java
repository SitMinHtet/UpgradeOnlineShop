package com.smh.myprojectone.service;

import com.smh.myprojectone.model.Product;
import com.smh.myprojectone.repository.ProductRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Data
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepo;


    @Override
    public Product create(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Product findById(int id) {
        return productRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Id cannot found"));
    }

    @Override
    public List<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public void deleteProductById(int id) {
        productRepo.deleteById(id);
    }



    /*
    @Override
    public void updateProductById(int id, Product product) {
        Product oldProduct = findById(id);
        oldProduct.setName(product.getName());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setDescription(product.getDescription());
        oldProduct.setImageName(product.getImageName());
        oldProduct.setCategory(product.getCategory());
    }
     */

    @Override
    public List<Product> productByCategoryId(int id) {
        return productRepo.findByCategoryId(id);
    }

}
