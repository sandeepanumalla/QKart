package com.example.qkart.service;

import com.example.qkart.model.CartItems;
import com.example.qkart.model.Orders;

import java.util.List;


public interface IOrderService {

    void createOrderWithCartCheckout(List<CartItems> cartItemsList);

    void createOrderWhenCartItemsDontExist(int cartId, int productId) throws Exception;

    void createOrderWithSingleCartItem(CartItems cartItems);

    List<Orders> getOrdersByUser();

    void cancelOrderById(int orderId);
}
