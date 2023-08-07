package com.example.qkart.repository;

import com.example.qkart.model.Orders;
import java.util.List;

public interface IOrderRepository {
    void save(Orders order);

    List<Orders> getAllOrders(int userId);

    void removeOrder(int orderId);

}
