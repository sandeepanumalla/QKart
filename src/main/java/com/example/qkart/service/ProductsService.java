package com.example.qkart.service;


import com.example.qkart.model.Product;
import com.example.qkart.repository.IProductRepository;

import java.util.List;

public class ProductsService implements IProductsService{

    public IProductRepository productRepository;

    public ProductsService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<Product> getAllProductsWithFilterApplied() {
        return productRepository.getAllProducts();
    }
}
