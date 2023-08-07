package com.example.qkart.repository;

import com.example.qkart.model.Orders;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class OrderRepository implements IOrderRepository{

    private final SessionFactory sessionFactory;

    public OrderRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Orders order) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(order);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<Orders> getAllOrders(int userId) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String query = "FROM Orders o where o.user.id = :userId";
            Query<Orders> ordersQuery = session.createQuery(query, Orders.class);
            ordersQuery.setParameter("userId", userId);
            List<Orders> ordersList= ordersQuery.getResultList();
            session.getTransaction().commit();
            return ordersList;
        }
    }

    @Override
    public void removeOrder(int orderId) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String query = "delete from Orders o where o.id = :orderId";
            Query<?> deleteQuery = session.createQuery(query);
            deleteQuery.setParameter("orderId", orderId);
            deleteQuery.executeUpdate();
            session.getTransaction().commit();
        }
    }
}
