package com.example.qkart.controller;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Home {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .buildSessionFactory();



        factory.close();
    }
}
