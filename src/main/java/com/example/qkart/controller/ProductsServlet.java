package com.example.qkart.controller;

import com.example.qkart.config.AppConfig;
import com.example.qkart.model.Product;
import com.example.qkart.model.User;
import com.example.qkart.repository.CartItemsRepository;
import com.example.qkart.repository.IUserRepository;
import com.example.qkart.service.ICartItemsService;
import com.example.qkart.service.ProductsService;

import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;


public class ProductsServlet extends HttpServlet {


    private AppConfig appConfig;

    private ICartItemsService cartItemsService;

    private IUserRepository userRepository;

    public ProductsServlet(){
    };


    @Override
    public void init() throws ServletException {
        appConfig = (AppConfig) getServletContext().getAttribute("appConfig");
        this.cartItemsService = appConfig.cartItemsService;
        this.userRepository = appConfig.userRepository;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
            listProducts(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void listProducts(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Product> productList = appConfig.productsService.getAllProductsWithFilterApplied();
            HttpSession httpSession = request.getSession();
            String username = (String) httpSession.getAttribute("username");
            if(username != null) {
                Optional<User> user = userRepository.finduserByUsername(username);
                int cartSize = 0;
                if (user.isPresent()) {
                    cartSize = cartItemsService.getCartSize(user.get().getCart().getId());
                }
                httpSession.setAttribute("cartSize", cartSize);
            }
            request.setAttribute("productList" , productList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/products.jsp");
            requestDispatcher.include(request, response);
//            response.sendRedirect("products");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
