package com.example.qkart.repository;

import java.util.List;
import java.util.Optional;

import com.example.qkart.model.Product;

public interface IProductRepository {

    void save(Product product);

    List<Product> getAllProducts();

    Optional<Product> getProductById(int productId);

}
