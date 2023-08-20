package com.example.qkart.controller;

import com.example.qkart.config.AppConfig;
import com.example.qkart.model.User;
import com.example.qkart.repository.IUserRepository;
import com.example.qkart.service.ICartItemsService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/api/protected/cart-items/remove")
public class RemoveCartItemsServlet extends HttpServlet {

    private ICartItemsService cartItemsService;

    private IUserRepository userRepository;

    @Override
    public void init() throws ServletException {
        AppConfig appConfig = (AppConfig) getServletContext().getAttribute("appConfig");
        this.userRepository = appConfig.userRepository;
        this.cartItemsService = appConfig.cartItemsService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            HttpSession httpSession = req.getSession();
            String productIdString = req.getParameter("productId");

            String username = (String) httpSession.getAttribute("username");
            Optional<User> user = userRepository.finduserByUsername(username);
            if(user.isEmpty()) {
                throw new RuntimeException("");
            }
            int cartId = user.get().getCart().getId();
            int productId = Integer.parseInt(productIdString);
            cartItemsService.removeProduct(cartId, productId);
            int cartSize = cartItemsService.getCartSize(cartId);
            httpSession.setAttribute("cartSize", cartSize);
            resp.sendRedirect(req.getContextPath() + "/cart");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
