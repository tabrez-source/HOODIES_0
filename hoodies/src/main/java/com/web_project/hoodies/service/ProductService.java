package com.web_project.hoodies.service;

import java.util.List;

import com.web_project.hoodies.model.Product;

public interface ProductService {
    Product saveProduct(Product product);
    Product updateProduct(Product product);
    void deleteProduct(Long id);
    Product getProductById(Long id);
    List<Product> getAllProducts();
    List<Product> searchProducts(String keyword);
}
