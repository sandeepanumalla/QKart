package com.example.qkart.service;

import com.example.qkart.model.Cart;
import com.example.qkart.model.CartItems;
import com.example.qkart.model.Product;
import com.example.qkart.repository.ICartItemsRepository;
import com.example.qkart.repository.ICartRepository;
import com.example.qkart.repository.IProductRepository;

import java.util.List;
import java.util.Optional;

public class CartItemsService implements ICartItemsService{

    private final ICartRepository cartRepository;

    private final ICartItemsRepository cartItemsRepository;

    private final IProductRepository productRepository;

    public CartItemsService(ICartRepository cartRepository, ICartItemsRepository cartItemsRepository, IProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.cartItemsRepository = cartItemsRepository;
        this.productRepository = productRepository;
    }


    @Override
    public void removeProduct(int cartItemsId) {
        cartItemsRepository.removeById(cartItemsId);
    }

    @Override
    public int getCartSize(int cartId) throws Exception {
        List<CartItems> cartItemsList = getCartItems(cartId);
        return cartItemsList.stream().mapToInt(CartItems::getQuantity).sum();
    }

    @Override
    public List<CartItems> getCartItems(int cartId) throws Exception {
        return cartItemsRepository.getCartItems(cartId);
    }

    @Override
    public double getTotalCartPrice(int cartId) throws Exception {
        List<CartItems> cartItemsList = getCartItems(cartId);
        return cartItemsList.stream().mapToDouble(cartItem -> cartItem.getQuantity() * cartItem.getProduct().getPrice()).sum();
    }

    @Override
    public CartItems getCartItemByProductId(int productId) {
        return cartItemsRepository.getCartItemByProductId(productId);
    }

    @Override
    public void clearCartItems(int cartId) throws Exception {
        cartItemsRepository.removeByCart(cartId);
    }

    @Override
    public void addCartItem(int cartId, int productId, int newQuantity) throws Exception {
        List<CartItems> cartItemsList = getCartItems(cartId);
        Optional<CartItems> cartItemsOptional = cartItemsList.stream().filter(cartItem -> cartItem.getProduct().getProductId() == productId).findFirst();
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
}
