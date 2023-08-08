package com.example.qkart.controller;

import com.example.qkart.config.AppConfig;
import com.example.qkart.model.CartItems;
import com.example.qkart.model.Product;
import com.example.qkart.model.User;
import com.example.qkart.repository.ICartRepository;
import com.example.qkart.repository.IProductRepository;
import com.example.qkart.repository.IUserRepository;
import com.example.qkart.service.ICartItemsService;
import com.example.qkart.service.ICartService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddToCartServlet extends HttpServlet {

    private AppConfig appConfig;

    private IProductRepository productRepository;

    private ICartService cartService;

    private ICartRepository cartRepository;
    private IUserRepository userRepository;

    private ICartItemsService cartItemsService;


    @Override
    public void init() throws ServletException {
        this.appConfig = (AppConfig)getServletContext().getAttribute("appConfig");
        this.productRepository = appConfig.productRepository;
        this.cartRepository = appConfig.cartRepository;
        this.userRepository = appConfig.userRepository;
        this.cartItemsService = appConfig.cartItemsService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productIdString = req.getParameter("productId");
        String quantityString = req.getParameter("quantity");
        HttpSession httpSession = req.getSession();
        String username = (String) httpSession.getAttribute("username");

        Optional<User> user  = userRepository.finduserByUsername(username);
        if(user.isEmpty()) {
            resp.sendRedirect("/cart");
        }

        int productId = Integer.parseInt(productIdString);
        int cartId = user.orElseThrow().getCart().getId();
        int initialCartSize = (int) httpSession.getAttribute("cartSize");
        int quantity = Integer.parseInt(quantityString);

        try {
            cartItemsService.addCartItem(cartId, productId, quantity);
            CartItems addedCartItems = cartItemsService.getCartItemByProductId(productId);
            req.setAttribute("addedCartItems", addedCartItems);
            int cartSize = cartItemsService.getCartSize(cartId);
            httpSession.setAttribute("cartSize", initialCartSize + cartSize);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/products");
            requestDispatcher.forward(req, resp);
//             request dispatcher or redirect
//            resp.sendRedirect(req.getContextPath() + "/products");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @SuppressWarnings("DuplicatedCode")
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productIdString = req.getParameter("productId");
        String quantityString = req.getParameter("quantity");
        String cartIdString = req.getParameter("cartId");
        String username = req.getParameter("username");

        int productId = Integer.parseInt(productIdString);
        int cartId = Integer.parseInt(cartIdString);
        int quantity = Integer.parseInt(quantityString);


        Optional<Product> product = productRepository.getProductById(Integer.parseInt(productIdString));

        try {
            cartItemsService.addCartItem(cartId, productId, quantity);
            CartItems addedCartItems = cartItemsService.getCartItemByProductId(productId);
            req.setAttribute("addedCartItems", addedCartItems);
            // request dispatcher or redirect
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/products.jsp");
            requestDispatcher.include(req, resp);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        cartItemsService.removeProduct(0);
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
