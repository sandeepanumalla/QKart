package com.example.qkart.model;

import java.util.ArrayList;
import java.util.List;

public class TempCart {
    private List<String> cartItems;

    public TempCart() {
        cartItems = new ArrayList<>();
    }

    public List<String> getCartItems() {
        return cartItems;
    }

    public void addProduct(String productId) {
        cartItems.add(productId);
    }

    public void removeProduct(String productId) {
        cartItems.remove(productId);
    }

    public void clearCart() {
        cartItems.clear();
    }

    public boolean isEmpty() {
        return cartItems.isEmpty();
    }

    public int getCartSize() {
        return cartItems.size();
    }
}
