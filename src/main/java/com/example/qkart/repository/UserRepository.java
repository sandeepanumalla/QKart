package com.example.qkart.repository;

import com.example.qkart.model.User;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class UserRepository implements IUserRepository{

    private final SessionFactory sessionFactory;
    private Session session;

    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(User user) {
        this.session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
//        User user = User.builder()
//                .kart(null)
//                .dateCreated(new Date())
//                .firstName("Sandeep")
//                .lastName("Anumalla")
//                .username("sanumalla")
//                .build();

        session.persist(user);
        transaction.commit();
        session.close();
    }

    @Override
    public Optional<User> finduserByUsername(String username) {
        this.session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String query = "FROM User where username = :username";
        Query userQuery = session.createQuery(query, User.class);
        userQuery.setParameter("username", username);
        List<User> users = userQuery.getResultList();
        transaction.commit();
        session.close();
        if(!users.isEmpty()) {
            return Optional.of(users.get(0));
        }
        return Optional.empty();
    }

    @Override
    public User findById(int userId) {
        return session.find(User.class, userId);
    }

}
