package com.example.qkart.service;

import com.example.qkart.model.CartItems;
import com.example.qkart.model.Orders;

import java.util.List;


public interface IOrderService {

    void createOrderWithCartCheckout(List<CartItems> cartItemsList);

    void createOrderWhenCartItemsDontExist(int cartId, int productId, int quantity) throws Exception;

    void createOrderWithSingleCartItem(CartItems cartItems);

    List<Orders> getOrdersByUser(int userId);

    void cancelOrderById(int orderId);
}
