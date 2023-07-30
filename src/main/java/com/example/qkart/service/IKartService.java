package com.example.qkart.service;

import com.example.qkart.dao.AddItemsRequest;
import com.example.qkart.model.Kart;
import com.example.qkart.model.Product;
import java.util.List;
import java.util.Map;

public interface IKartService {

//    List<Kart> getKartItemsForUser(Map<Product, Integer> productsWithQuantities, int userId);

    public void addItemToCart(int userId, Map<Product, Integer> productsWithQuantities);

    int getProductCountByUser();

}
