package com.web_project.hoodies.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web_project.hoodies.model.Product;
import com.web_project.hoodies.service.ProductService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
       this.productService = productService;
    }

    // Display all products
    @GetMapping
    public String listProducts(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        List<Product> products;
        if (keyword != null && !keyword.isEmpty()) {
            products = productService.searchProducts(keyword);
        } else {
            products = productService.getAllProducts();
        }
        model.addAttribute("products", products);
        return "products";
    }

    // Display product details
    @GetMapping("/{id}")
    public String productDetails(@PathVariable Long id, Model model) {
       Product product = productService.getProductById(id);
       model.addAttribute("product", product);
       return "product-details";
    }

    // Admin: Show form to add a new product
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        return "product-form";
    }

    // Admin: Save new product
    @PostMapping
    public String saveProduct(@Valid @ModelAttribute("product") Product product,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product-form";
        }
        productService.saveProduct(product);
        return "redirect:/products";
    }

    // Admin: Show form to edit a product
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
       Product product = productService.getProductById(id);
       model.addAttribute("product", product);
       return "product-form";
    }

    // Admin: Update product
    @PostMapping("/{id}")
    public String updateProduct(@PathVariable Long id,
                                @Valid @ModelAttribute("product") Product product,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product-form";
        }
        product.setId(id);
        productService.updateProduct(product);
        return "redirect:/products";
    }

    // Admin: Delete product
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
       productService.deleteProduct(id);
       return "redirect:/products";
    }
}
