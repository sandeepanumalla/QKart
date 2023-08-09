package com.example.qkart.controller;

import com.example.qkart.config.AppConfig;
import com.example.qkart.model.CartItems;
import com.example.qkart.service.IOrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.util.List;

@WebServlet("/api/protected/checkout-all")
public class CheckoutAllCartItemsServlet extends HttpServlet {

    private AppConfig appConfig;

    private IOrderService orderService;


    @Override
    public void init() throws ServletException {
        AppConfig appConfig = (AppConfig) getServletContext().getAttribute("appConfig");
        this.orderService = appConfig.orderService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get all the list of cartItems
        HttpSession httpSession = req.getSession();
        List<CartItems> cartItemsList = (List<CartItems>) httpSession.getAttribute("cartItemsList");

        try {
            // call the createOrders method
            orderService.createOrderWithCartCheckout(cartItemsList);

            // redirect to orders page
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/orders");
            requestDispatcher.forward(req, resp);
        } catch (RuntimeException exception) {
            resp.sendRedirect("/cart");
        }
    }
}
