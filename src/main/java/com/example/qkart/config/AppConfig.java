package com.example.qkart.config;

import com.example.qkart.repository.*;
import com.example.qkart.service.IUserService;
import com.example.qkart.service.ProductsService;
import com.example.qkart.service.UserService;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.modelmapper.ModelMapper;


public class AppConfig {

    private final SessionFactory sessionFactory = SessionProvider.getSessionFactory();

    public Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public ModelMapper modelMapper= new ModelMapper();

    public IProductRepository productRepository = new ProductRepository(sessionFactory);

    public ProductsService productsService = new ProductsService(productRepository);

    public IUserRepository userRepository = new UserRepository(sessionFactory);

    public IUserService userService = new UserService(userRepository, modelMapper, validator);

}
