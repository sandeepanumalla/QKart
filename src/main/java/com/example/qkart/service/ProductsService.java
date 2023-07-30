package com.example.qkart.service;


import com.example.qkart.model.Product;
import com.example.qkart.repository.IProductRepository;

import java.util.List;

public class ProductsService {

    public final IProductRepository productRepository;

    public ProductsService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }
}
