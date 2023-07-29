package com.example.qkart.service;

import com.example.qkart.model.Product;
import java.util.List;

public interface IKartService {

    List<Product> getAllProductsByUserId();

    int getProductCountByUser();

}
