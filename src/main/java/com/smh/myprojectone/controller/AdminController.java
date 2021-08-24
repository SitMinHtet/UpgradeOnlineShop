package com.smh.myprojectone.controller;

import com.smh.myprojectone.dto.ProductDto;
import com.smh.myprojectone.model.Category;
import com.smh.myprojectone.model.Product;
import com.smh.myprojectone.service.CategoryService;
import com.smh.myprojectone.service.ProductService;
import lombok.Data;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@Data
@RequestMapping("/admin")
public class AdminController {

    private final CategoryService categoryService;
    private final ProductService productService;

    public static final String uploadDir = System.getProperty("user.dir")+"/src/main/resources/static/images/productImages";

    @GetMapping()
    public String adminHomePage(){
        return "adminHome";
    }


    //Category Section
    @GetMapping("categories/add")
    public String adminAddCategory(Model model){
        model.addAttribute("category", new Category());
        return "categoriesAdd";
    }

    @PostMapping("/categories/add")
    public String process(@Valid Category category, Model model, BindingResult result){
        if(result.hasErrors()){
            return "categoriesAdd";
        }
        categoryService.create(category);
        return "redirect:/admin/categories";
    }
    @GetMapping("categories")
    public String showAllCategory(Model model){
        model.addAttribute("categories", categoryService.findAll());
        return "categories";
    }

    @GetMapping("/categories/delete/{id}")
    public String deleteCategory(@PathVariable("id")int id, Model model){
        categoryService.deleteCategoryById(id);
        return "redirect:/admin/categories";
    }

    @GetMapping("/categories/update/{id}")
    public String updateCategory(@PathVariable("id")int id, Model model){
        model.addAttribute("category", categoryService.findById(id));
        return "categoriesUpdate";

    }

    @PostMapping("/categoryUpdate/{id}")
    public String processUpdate(@PathVariable int id,@Valid Category category, BindingResult result) {

        if (result.hasErrors()) {
            category.setId(id);
            return "categoriesUpdate";
        }

        categoryService.create(category);
        return "redirect:/admin/categories";
    }
    //End Category Section

    //Product Section
    @GetMapping("products/add")
    public String adminAddProduct(Model model){
        model.addAttribute("productDTO", new ProductDto());
        model.addAttribute("categories", categoryService.findAll());
        return "productsAdd";
    }

    @PostMapping("/products/add")
    public String processProductAdd(@ModelAttribute("productDto") ProductDto productDto,
                                    @RequestParam("productImage") MultipartFile multipartFile,
                                    @RequestParam("imgName") String imageName,Model model , BindingResult result) throws IOException {

        if(result.hasErrors()){
            model.addAttribute("category", categoryService.findAll());
            return "productsAdd";
        }


        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setQuantity(productDto.getQuantity());
        product.setPrice(productDto.getPrice());
        product.setCategory(categoryService.findById(productDto.getCategoryId()));
        product.setDescription(productDto.getDescription());


        String chooseImage;
        if (!multipartFile.isEmpty()){
            chooseImage = multipartFile.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, chooseImage);
            Files.write(filePath,multipartFile.getBytes());
        }
        else {
            chooseImage = imageName;
        }
        product.setImageName(chooseImage);


        productService.create(product);
        return "redirect:/admin/products";
    }

    @GetMapping("products")
    public String showAllProduct(Model model){
        model.addAttribute("products", productService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        return "products";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id, Model model){
        productService.deleteProductById(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/products/update/{id}")
    public String updateProduct(@PathVariable("id")int id, Model model){
        model.addAttribute("product", productService.findById(id));
        model.addAttribute("categories", categoryService.findAll());
        //model.addAttribute("productDto", productService.productByCategoryId(id));
        return "productsUpdate";
    }

    @PostMapping("/productUpdate/{id}")
    public String processUpdateProduct(@PathVariable int id,@Valid Product product,
                                       BindingResult result,
                                       @RequestParam("imgName") String imageName,
                                       @RequestParam("productImage") MultipartFile multipartFile) throws IOException {

        if (result.hasErrors()) {
            product.setId(id);
            return "productsUpdate";
        }
        if(imageName != null && !imageName.isEmpty()) {
            String chooseImage;
            if (!multipartFile.isEmpty()){
                chooseImage = multipartFile.getOriginalFilename();
                Path filePath = Paths.get(uploadDir, chooseImage);
                Files.write(filePath,multipartFile.getBytes());
            }
            else {
                chooseImage = imageName;
            }
            product.setImageName(chooseImage);
        }

        productService.create(product);
        return "redirect:/admin/products";
    }

    //End Product Section



}
