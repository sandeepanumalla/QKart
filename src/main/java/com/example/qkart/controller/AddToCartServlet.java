package com.example.qkart.controller;

import com.example.qkart.config.AppConfig;
import com.example.qkart.model.Product;
import com.example.qkart.model.User;
import com.example.qkart.repository.IKartRepository;
import com.example.qkart.repository.IProductRepository;
import com.example.qkart.repository.IUserRepository;
import com.example.qkart.service.IKartService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddToCartServlet extends HttpServlet {

    private AppConfig appConfig;

    private IProductRepository productRepository;

    private IKartService kartService;

    private IKartRepository kartRepository;
    private IUserRepository userRepository;

//    private

    @Override
    public void init() throws ServletException {
        this.appConfig = (AppConfig)getServletContext().getAttribute("appConfig");
        this.productRepository = appConfig.productRepository;
        this.kartService = appConfig.kartService;
        this.kartRepository = appConfig.kartRepository;
        this.userRepository = appConfig.userRepository;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("productId");
        String quantity = req.getParameter("quantity");
        String username = req.getParameter("username");

        Optional<Product> product = productRepository.getProductById(Integer.parseInt(productId));

        Product product1 = product.orElseThrow(() -> new IllegalArgumentException(""));

        Map<Product, Integer> productIntegerMap = new HashMap<>();
        productIntegerMap.put(product1, Integer.parseInt(quantity));

        System.out.println(productId);
        Optional<User> user = userRepository.finduserByUsername(username);
        User user1 = user.orElseThrow(() -> new IllegalArgumentException(""));
        int userIdInt = user1.getUserId();
        kartService.addItemToCart( userIdInt, productIntegerMap);




    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
