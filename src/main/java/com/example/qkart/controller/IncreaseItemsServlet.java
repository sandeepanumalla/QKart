package com.example.qkart.controller;

import com.example.qkart.config.AppConfig;
import com.example.qkart.model.CartItems;
import com.example.qkart.model.User;
import com.example.qkart.repository.IUserRepository;
import com.example.qkart.service.ICartItemsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@WebServlet("/api/protected/increase-items")
public class IncreaseItemsServlet extends HttpServlet {
    private ICartItemsService cartItemsService;

    private IUserRepository userRepository;

    @Override
    public void init() throws ServletException {
        AppConfig appConfig = (AppConfig) getServletContext().getAttribute("appConfig");
        this.cartItemsService = appConfig.cartItemsService;
        this.userRepository = appConfig.userRepository;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        String username = (String) httpSession.getAttribute("username");
        String productIdString = req.getParameter("productId");
        String quantityString = req.getParameter("quantity");

        try {
            Optional<User> user = userRepository.finduserByUsername(username);
            if(user.isEmpty()) {
                throw new RuntimeException("no user found");
            }
            // the item to cart
            int productId = Integer.parseInt(productIdString);
            int cartId = user.orElseThrow().getCart().getId();
            int quantity = Integer.parseInt(quantityString) + 1;
            cartItemsService.addCartItem(cartId, productId, quantity);

            //get updated items from cart
            List<CartItems> cartItemsList = cartItemsService.getCartItems(cartId);
            double totalCartPrice = cartItemsService.getTotalCartPrice(cartId);
            int cartSize = cartItemsService.getCartSize(cartId);
            httpSession.setAttribute("cartItemsList", cartItemsList);
            httpSession.setAttribute("totalCartPrice", totalCartPrice);
            httpSession.setAttribute("cartSize", cartSize);
            resp.sendRedirect(req.getContextPath() + "/cart");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
