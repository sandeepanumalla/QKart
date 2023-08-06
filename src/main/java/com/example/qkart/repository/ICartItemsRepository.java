package com.example.qkart.repository;

import com.example.qkart.model.CartItems;

import java.util.List;

public interface ICartItemsRepository {
    List<CartItems> findByCart(int cartId);

    void removeById(int cartItemsId);

    void saveCartItem(CartItems cartItems);
}
