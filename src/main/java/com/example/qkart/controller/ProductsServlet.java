package com.example.qkart.controller;

import com.example.qkart.config.AppConfig;
import com.example.qkart.model.Product;
import com.example.qkart.service.ProductsService;

import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;


public class ProductsServlet extends HttpServlet {


    private AppConfig appConfig;

    public ProductsServlet(){
    };


    @Override
    public void init() throws ServletException {
        appConfig = (AppConfig) getServletContext().getAttribute("appConfig");
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
            request.setAttribute("productList" , productList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/products.jsp");
            requestDispatcher.include(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
