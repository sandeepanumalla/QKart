package com.example.qkart.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/orders")
public class OrdersAuthorizationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        boolean isAuthorized = false;

        String username;
        HttpSession httpSession = req.getSession();
        username = (String) httpSession.getAttribute("username");
        isAuthorized = username != null;
        if(isAuthorized) {
            req.getRequestDispatcher("/api/protected/orders").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/orders");
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
