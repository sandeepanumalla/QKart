package com.example.qkart.controller;

import com.example.qkart.config.AppConfig;
import com.example.qkart.model.Orders;
import com.example.qkart.model.User;
import com.example.qkart.repository.IUserRepository;
import com.example.qkart.service.IOrderService;
import com.example.qkart.service.IUserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

import java.util.List;

@WebServlet("/api/protected/orders")
public class OrdersServlet extends HttpServlet {

    private AppConfig appConfig;

    private IOrderService orderService;

    private IUserRepository userRepository;


    @Override
    public void init() throws ServletException {
        AppConfig appConfig = (AppConfig) getServletContext().getAttribute("appConfig");
        this.userRepository = appConfig.userRepository;
        this.orderService = appConfig.orderService;
    }


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //retrieve orders and
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");
        Optional<User> user = userRepository.finduserByUsername(username);
        if(user.isEmpty()) {
            resp.sendRedirect("/cart");
        }
        List<Orders> ordersList = orderService.getOrdersByUser(user.get().getUserId());
        session.setAttribute("ordersList", ordersList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/orders");
        requestDispatcher.forward(req, resp);
        // set session
    }

}
