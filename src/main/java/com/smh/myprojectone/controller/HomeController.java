package com.smh.myprojectone.controller;

import com.smh.myprojectone.service.CategoryService;
import com.smh.myprojectone.service.ProductService;
import lombok.Data;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@Data
public class HomeController {

    private final CategoryService categoryService;
    private final ProductService productService;


    @GetMapping({"/", "/home"})
    public String homePage(Model model){
        return "index";
    }

    @GetMapping("shop")
    public String shopping(Model model){
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("products", productService.findAll());
        return "shop";
    }

    @GetMapping("shop/category/{id}")
    public String shopByCategoryId(Model model,@PathVariable int id){
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("products", productService.productByCategoryId(id));
        return "shop";
    }

    @GetMapping("shop/viewproduct/{id}")
    public String viewProduct(@PathVariable int id, Model model){
        model.addAttribute("product", productService.findById(id));
        return "viewProduct";
    }
}
