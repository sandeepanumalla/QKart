package com.example.qkart.controller;

import com.example.qkart.config.AppConfig;
import com.example.qkart.model.CartItems;
import com.example.qkart.model.User;
import com.example.qkart.repository.IUserRepository;
import com.example.qkart.service.ICartItemsService;
import com.example.qkart.service.IOrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@WebServlet("/api/protected/buy-now")
public class BuyNowServlet extends HttpServlet {


    private AppConfig appConfig;

    private IOrderService orderService;

    private IUserRepository userRepository;

    private ICartItemsService cartItemsService;


    @Override
    public void init() throws ServletException {
        AppConfig appConfig = (AppConfig) getServletContext().getAttribute("appConfig");
        this.userRepository = appConfig.userRepository;
        this.orderService = appConfig.orderService;
        this.cartItemsService = appConfig.cartItemsService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get all the list of cartItems
        HttpSession httpSession = req.getSession();
        String username = (String) httpSession.getAttribute("username");
        String productIdString = req.getParameter("productId");
        String quantityString = req.getParameter("quantity");
            if(quantityString == null) {
            quantityString = "1";
        }
        Optional<User> user = userRepository.finduserByUsername(username);

        try {
            int cartId  = user.get().getCart().getId();
            int productId = Integer.parseInt(productIdString);
            int quantity = Integer.parseInt(quantityString);
            // call the createOrders method
            orderService.createOrderWhenCartItemsDontExist(cartId, productId, quantity);

            int cartSize = cartItemsService.getCartSize(cartId);
            httpSession.setAttribute("cartSize", cartSize);

            resp.sendRedirect("orders");
        }
        catch ( RuntimeException exception) {
            resp.sendRedirect("/cart");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
