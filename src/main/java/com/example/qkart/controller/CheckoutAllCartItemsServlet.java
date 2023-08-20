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

@WebServlet("/api/protected/checkout-all")
public class CheckoutAllCartItemsServlet extends HttpServlet {

    private AppConfig appConfig;

    private IOrderService orderService;
    private IUserRepository userRepository;

    private ICartItemsService cartItemsService;


    @Override
    public void init() throws ServletException {
        AppConfig appConfig = (AppConfig) getServletContext().getAttribute("appConfig");
        this.orderService = appConfig.orderService;
        this.userRepository = appConfig.userRepository;
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
        List<CartItems> cartItemsList = (List<CartItems>) httpSession.getAttribute("cartItemsList");

        try {
            // call the createOrders method
            orderService.createOrderWithCartCheckout(cartItemsList);
            String username = (String) httpSession.getAttribute("username");
            if(username != null) {
                Optional<User> user = userRepository.finduserByUsername(username);
                int cartSize = 0;
                if (user.isPresent()) {
                    cartSize = cartItemsService.getCartSize(user.get().getCart().getId());
                }
                httpSession.setAttribute("cartSize", cartSize);
            }
            resp.sendRedirect("orders");
        } catch (RuntimeException exception) {
            System.out.println(exception.getMessage());
            resp.sendRedirect("/cart");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
