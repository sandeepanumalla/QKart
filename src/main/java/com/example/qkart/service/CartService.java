package com.example.qkart.service;

import com.example.qkart.repository.ICartItemsRepository;
import com.example.qkart.repository.ICartRepository;
import com.example.qkart.repository.IProductRepository;
import com.example.qkart.repository.IUserRepository;


import java.util.List;
import java.util.*;

public class CartService implements ICartService {

    private final ICartRepository cartRepository;
    private final IUserRepository userRepository;

    private final ICartItemsRepository cartItemsRepository;

    private final IProductRepository productRepository;

    public CartService(ICartRepository cartRepository, IUserRepository userRepository, ICartItemsRepository cartItemsRepository, IProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.cartItemsRepository = cartItemsRepository;
        this.productRepository = productRepository;
    }


//    @Override
//    public void addItemToCart(int userId, Map<Product, Integer> productsWithQuantities) {
//        if(productsWithQuantities == null || productsWithQuantities.isEmpty()) {
//            throw new IllegalArgumentException("Invalid products with quantities to add.");
//        }
//        List<Cart> cart = cartRepository.getProductsByUserId(userId);
//
//        for(Map.Entry<Product, Integer> entry: productsWithQuantities.entrySet()) {
//            Product product = entry.getKey();
//            int quantityToAdd = entry.getValue();
//
//            if(product == null || quantityToAdd <= 0) {
//                throw new IllegalArgumentException("Invalid products with quantities to add.");
//            }
//
////            User user = userRepository.findById(userId);
////            Cart.KartProductKey kartProductKey = new Cart.KartProductKey(user, product);
////            Cart existingKartItem = cartRepository.findById(kartProductKey);
//
////            if(existingKartItem != null) {
////                int currentQuantity = existingKartItem.getQuantity();
////                existingKartItem.setQuantity(currentQuantity + quantityToAdd);
////                cartRepository.update(existingKartItem);
////            } else {
////                Cart newKartItem = Cart.builder()
////                        .id(kartProductKey)
////                        .quantity(quantityToAdd)
////                        .build();
////                cartRepository.save(newKartItem);
////            }
//
//        }
//    }

//    @Override
//    public int getProductCountByUser() {
//        return 0;
//    }

//    @Override
//    public List<CartItems> getCartItems(int cartId) {
//        return null;
//    }


    @Override
    public boolean isEmpty() {
        return false;
    }



}
