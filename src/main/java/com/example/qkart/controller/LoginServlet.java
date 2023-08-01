package com.example.qkart.controller;

import com.example.qkart.config.AppConfig;
import com.example.qkart.dao.UserLoginRequest;
import com.example.qkart.service.IUserService;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private AppConfig appConfig;
    private Session session;

    private IUserService userService;

    @Override
    public void init() throws ServletException {
        this.appConfig = (AppConfig)getServletContext().getAttribute("appConfig");
        this.userService = appConfig.userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserLoginRequest userLoginRequest = new UserLoginRequest(username, password);

        try {
            userService.login(userLoginRequest);
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("username", username);
            resp.sendRedirect("Products.jsp");
        } catch (Exception e) {
            resp.sendRedirect(req.getContextPath() + "/Login.jsp?error=1");
//            throw new RuntimeException(e);
        }
    }
}
