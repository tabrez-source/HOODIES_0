package com.web_project.hoodies.service;

import java.util.List;

import com.web_project.hoodies.model.Cart;
import com.web_project.hoodies.model.CartItem;
import com.web_project.hoodies.model.User;

public interface CartService {
    void addProductToCart(User user, Long productId, int quantity);
    void removeProductFromCart(User user, Long productId);
    void updateProductQuantity(User user, Long productId, int quantity);
    List<CartItem> getCartItems(User user);
    void clearCart(User user);
    Cart getCartWithItems(User user);
}
