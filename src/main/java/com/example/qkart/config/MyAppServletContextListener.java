package com.example.qkart.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyAppServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("invoking scl");
        AppConfig appConfig = new AppConfig();

        sce.getServletContext().setAttribute("appConfig", appConfig);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("being destroyed!!!");
        ServletContextListener.super.contextDestroyed(sce);
    }
}
