package com.example.qkart.service;

import com.example.qkart.model.Cart;
import com.example.qkart.model.CartItems;
import com.example.qkart.model.Product;
import com.example.qkart.repository.ICartItemsRepository;
import com.example.qkart.repository.ICartRepository;
import com.example.qkart.repository.IProductRepository;
import com.example.qkart.repository.IUserRepository;


import java.util.List;
import java.util.*;

public class KartService implements IKartService{

    private final ICartRepository cartRepository;
    private final IUserRepository userRepository;

    private final ICartItemsRepository cartItemsRepository;

    private final IProductRepository productRepository;

    public KartService(ICartRepository cartRepository, IUserRepository userRepository, ICartItemsRepository cartItemsRepository, IProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.cartItemsRepository = cartItemsRepository;
        this.productRepository = productRepository;
    }


    @Override
    public void addItemToCart(int userId, Map<Product, Integer> productsWithQuantities) {
        if(productsWithQuantities == null || productsWithQuantities.isEmpty()) {
            throw new IllegalArgumentException("Invalid products with quantities to add.");
        }
        List<Cart> cart = cartRepository.getProductsByUserId(userId);

        for(Map.Entry<Product, Integer> entry: productsWithQuantities.entrySet()) {
            Product product = entry.getKey();
            int quantityToAdd = entry.getValue();

            if(product == null || quantityToAdd <= 0) {
                throw new IllegalArgumentException("Invalid products with quantities to add.");
            }

//            User user = userRepository.findById(userId);
//            Cart.KartProductKey kartProductKey = new Cart.KartProductKey(user, product);
//            Cart existingKartItem = cartRepository.findById(kartProductKey);

//            if(existingKartItem != null) {
//                int currentQuantity = existingKartItem.getQuantity();
//                existingKartItem.setQuantity(currentQuantity + quantityToAdd);
//                cartRepository.update(existingKartItem);
//            } else {
//                Cart newKartItem = Cart.builder()
//                        .id(kartProductKey)
//                        .quantity(quantityToAdd)
//                        .build();
//                cartRepository.save(newKartItem);
//            }

        }
    }

    @Override
    public int getProductCountByUser() {
        return 0;
    }

    @Override
    public List<CartItems> getCartItems(int cartId) {
        return null;
    }

    @Override
    public void addCartItem(int cartId, int productId, int newQuantity) {
        List<CartItems> cartItemsList = getCartItems(cartId);

        Optional<CartItems> cartItemsOptional = cartItemsList.stream().filter(cartItem -> cartItem.getProduct().getProductId() == productId).findAny();
        Cart cart = cartRepository.findById(cartId);
        Product product = productRepository.getProductById(productId).orElseThrow(() -> new IllegalArgumentException("no product found with productId" + productId));
        boolean productExists = cartItemsOptional.isPresent();
        CartItems cartItem;
        if(productExists) {
             cartItem =  cartItemsOptional.get();
                cartItem.setQuantity(newQuantity);
        } else {
             cartItem = CartItems.builder()
                    .cart(cart)
                    .quantity(newQuantity)
                    .product(product)
                    .build();
        }
        cartItemsRepository.saveCartItem(cartItem);
    }


    @Override
    public void removeProduct(int cartItemsId) {
        cartItemsRepository.removeById(cartItemsId);
    }

    @Override
    public void clearCart() {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int getCartSize() {
        return 0;
    }


}
