package com.example.qkart.views;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("username") != null) {
            resp.sendRedirect("products");
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/Register.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}
