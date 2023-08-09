package com.example.qkart.config;

import com.example.qkart.repository.*;
import com.example.qkart.service.*;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.hibernate.SessionFactory;
import org.modelmapper.ModelMapper;


public class AppConfig {

    private final SessionFactory sessionFactory = SessionProvider.getSessionFactory();

    public Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public ModelMapper modelMapper= new ModelMapper();


    public IProductRepository productRepository = new ProductRepository(sessionFactory);

    public IUserRepository userRepository = new UserRepository(sessionFactory);

    public ICartRepository cartRepository = new CartRepository(sessionFactory);

    public IOrderRepository orderRepository = new OrderRepository(sessionFactory);
    public ProductsService productsService = new ProductsService(productRepository);

    private final ICartItemsRepository cartItemsRepository = new CartItemsRepository(sessionFactory);

    public IUserService userService = new UserService(userRepository, cartRepository, modelMapper, validator);

    public ICartService cartService = new CartService(cartRepository, userRepository, cartItemsRepository, productRepository);

    public ICartItemsService cartItemsService = new CartItemsService(cartRepository, cartItemsRepository, productRepository);

    public IOrderService orderService = new OrderService(orderRepository, cartItemsService);
}
