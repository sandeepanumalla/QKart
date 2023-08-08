package com.example.qkart.service;

import com.example.qkart.model.CartItems;

import java.util.List;

public interface ICartItemsService {

    void addCartItem(int cartId, int productId, int newQuantity) throws Exception;

    public void removeProduct(int cartId, int productId);

    public int getCartSize(int cartId) throws Exception;

    List<CartItems> getCartItems(int cartId) throws Exception;

    double getTotalCartPrice(int cartId) throws Exception;

    CartItems getCartItemByProductId(int productId);

    public void clearCartItems(int cartId) throws Exception;

}
