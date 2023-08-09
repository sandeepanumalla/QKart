package com.example.qkart.controller;

import com.example.qkart.config.AppConfig;
import com.example.qkart.model.CartItems;
import com.example.qkart.model.User;
import com.example.qkart.repository.IUserRepository;
import com.example.qkart.service.CartItemsService;
import com.example.qkart.service.ICartItemsService;
import lombok.NoArgsConstructor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;
import java.util.List;


public class CartItemsServlet extends HttpServlet {

    private ICartItemsService cartItemsService;

    private IUserRepository userRepository;

    @Override
    public void init() throws ServletException {
        AppConfig appConfig = (AppConfig) getServletContext().getAttribute("appConfig");
        this.cartItemsService = appConfig.cartItemsService;
        this.userRepository = appConfig.userRepository;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        String username = (String) httpSession.getAttribute("username");
        try {
            Optional<User> user = userRepository.finduserByUsername(username);
            if(user.isEmpty()) {
                throw new RuntimeException("no user found");
            }
            int cartId = user.get().getCart().getId();
            List<CartItems> cartItemsList = cartItemsService.getCartItems(cartId);
            double totalCartPrice = cartItemsService.getTotalCartPrice(cartId);
            httpSession.setAttribute("cartItemsList", cartItemsList);
            httpSession.setAttribute("totalCartPrice", totalCartPrice);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/cart");
            requestDispatcher.forward(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
