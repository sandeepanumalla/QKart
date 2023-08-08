package com.example.qkart.controller;

import com.example.qkart.config.AppConfig;
import com.example.qkart.dao.UserLoginRequest;
import com.example.qkart.model.User;
import com.example.qkart.repository.ICartItemsRepository;
import com.example.qkart.repository.IUserRepository;
import com.example.qkart.service.ICartItemsService;
import com.example.qkart.service.IUserService;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class LoginServlet extends HttpServlet {

    private AppConfig appConfig;
    private Session session;

    private IUserService userService;

    private IUserRepository userRepository;

    private ICartItemsService cartItemsService;


    @Override
    public void init() throws ServletException {
        this.appConfig = (AppConfig)getServletContext().getAttribute("appConfig");
        this.cartItemsService = ((AppConfig) getServletContext().getAttribute("appConfig")).cartItemsService;
        this.userRepository = ((AppConfig) getServletContext().getAttribute("appConfig")).userRepository;
        this.userService = appConfig.userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserLoginRequest userLoginRequest = new UserLoginRequest(username, password);


        try {
            userService.login(userLoginRequest);
            Optional<User> user = userRepository.finduserByUsername(username);
            int cartSize = cartItemsService.getCartSize(user.get().getUserId());
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("cartSize", cartSize);
            httpSession.setAttribute("username", username);
            req.getRequestDispatcher("/products").forward(req, resp);
//            resp.sendRedirect("Products.jsp");
        } catch (Exception e) {
            resp.sendRedirect(req.getContextPath() + "/Login.jsp?error=1");
        }
    }
}
