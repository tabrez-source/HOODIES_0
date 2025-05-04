package com.web_project.hoodies.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web_project.hoodies.model.Product;
import com.web_project.hoodies.service.ProductService;

@Controller
public class HomeController {

    private final ProductService productService;
    
    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping({"/", "/home"})
    public String home(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Product> products;

        if (keyword != null && !keyword.trim().isEmpty()) {
            products = productService.searchProducts(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            products = productService.getAllProducts().subList(0, 9);
        }

        model.addAttribute("products", products);
        return "home";
    }

    @GetMapping("/about")
    public String aboutUs(Model model) {
        return "about";
    }
}
