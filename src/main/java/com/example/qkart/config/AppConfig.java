package com.example.qkart.config;

import com.example.qkart.repository.IProductRepository;
import com.example.qkart.repository.ProductRepository;
import com.example.qkart.repository.SessionProvider;
import com.example.qkart.service.ProductsService;
import org.hibernate.Session;


public class AppConfig {

    private final Session session = SessionProvider.createSession();

    public IProductRepository productRepository = new ProductRepository(session);

    public ProductsService productsService = new ProductsService(productRepository);



}
