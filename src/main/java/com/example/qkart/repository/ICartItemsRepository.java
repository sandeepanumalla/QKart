package com.example.qkart.repository;

import com.example.qkart.model.CartItems;

import java.util.List;

public interface ICartItemsRepository {

    void removeById(int cartId, int productId);

    void saveCartItem(CartItems cartItems);

    public List<CartItems> getCartItems(int cartId) throws Exception;

    void removeByCart(int cartId);

    CartItems getCartItemByProductId(int productId);

}
