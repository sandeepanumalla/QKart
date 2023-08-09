package com.example.qkart.controller;

import com.example.qkart.config.AppConfig;
import com.example.qkart.model.CartItems;
import com.example.qkart.model.User;
import com.example.qkart.repository.IUserRepository;
import com.example.qkart.service.ICartItemsService;
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
import java.util.Optional;

@WebServlet("/api/protected/order/cancel")
public class CancelOrderServlet extends HttpServlet {

    private IOrderService orderService;


    @Override
    public void init() throws ServletException {
        AppConfig appConfig = (AppConfig) getServletContext().getAttribute("appConfig");
        this.orderService = appConfig.orderService;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        String orderIdString = (String) req.getParameter("orderId");
        try {
            int orderId = Integer.parseInt(orderIdString);
            orderService.cancelOrderById(orderId);
//            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/orders");
//            requestDispatcher.forward(req, resp);
            resp.sendRedirect(req.getContextPath() + "/orders");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}