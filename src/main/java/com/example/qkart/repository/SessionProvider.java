package com.example.qkart.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionProvider {

    private static SessionFactory sessionFactory = null;
    private static Session session = null;
    public static SessionFactory getSessionFactory() {
         sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

       return sessionFactory;
    }

    public static Session createSession() {
        session = getSessionFactory().openSession();
        return session;
    }


    public void closeSessionFactory() {
        sessionFactory.close();
    }

    public void closeSession() {
        session.close();
    }
}
