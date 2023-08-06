package com.example.qkart.service;

import com.example.qkart.model.CartItems;
import com.example.qkart.model.Product;

import java.util.List;
import java.util.Map;

public interface IKartService {

//    List<Cart> getKartItemsForUser(Map<Product, Integer> productsWithQuantities, int userId);

    public void addItemToCart(int userId, Map<Product, Integer> productsWithQuantities);

    int getProductCountByUser();

    List<CartItems> getCartItems(int cartId);

    void addCartItem(int cartId, int productId, int newQuantity);

    public void removeProduct(int cartItemsId);

    public void clearCart();

    public boolean isEmpty();

    public int getCartSize();
}
