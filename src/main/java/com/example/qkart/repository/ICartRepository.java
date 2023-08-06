package com.example.qkart.repository;

import com.example.qkart.model.Cart;

import java.util.List;


public interface ICartRepository {

    void save(Cart cart);

    List<Cart> getProductsByUserId(int userId);

    public Cart findById(int cartId);

    void update(Cart existingCartItem);

    int getTotalPrice(int cartId);

    int getCartSize(int cartId);
}
