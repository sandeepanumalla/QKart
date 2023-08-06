package com.example.qkart.service;

import com.example.qkart.model.Orders;

import java.util.List;


public interface IOrderService {
    List<Orders> getOrdersByUser();

    void cancelOrderById(int orderId);
}
