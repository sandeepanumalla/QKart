package com.example.qkart.controller;

import com.example.qkart.config.AppConfig;
import com.example.qkart.dao.UserRegisterRequest;
import com.example.qkart.service.IUserService;
import jakarta.persistence.PersistenceException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;


public class RegisterServlet extends HttpServlet {

//    private ModelMapper mapper;
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
        String firstname = req.getParameter("firstname");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");

        UserRegisterRequest userRegisterRequest = new UserRegisterRequest(username, firstname, password, confirmPassword);

        try {
            userService.register(userRegisterRequest);
            req.setAttribute("registrationSuccess", username);
            req.getRequestDispatcher("/Login.jsp").forward(req, resp);
        } catch (ConstraintViolationException constraintViolationException) {
            String errorMessage = "An error occurred during registration: " + constraintViolationException.getMessage();
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/Register.jsp").forward(req, resp);
        } catch (PersistenceException persistenceException) {
            if (persistenceException.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
                // Handle specific database constraint violations
                String errorMessage = "Registration failed. " + persistenceException.getMessage();
                req.setAttribute("errorMessage", errorMessage);
            } else {
                // Handle other persistence-related exceptions
                String errorMessage = "Registration failed due to a database issue. Please try again later.";
                req.setAttribute("errorMessage", errorMessage);
            }
            req.getRequestDispatcher("/Register.jsp").forward(req, resp);
        } catch (Exception exception) {
            String errorMessage = "Internal server error occurred. Please contact support.";
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/Register.jsp").forward(req, resp);
        }
    }
}
