package com.example.qkart.repository;

import com.example.qkart.model.Kart;
import com.example.qkart.model.Product;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class KartRepository implements IKartRepository{

    private final SessionFactory sessionFactory;
    private Session session;

    public KartRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Kart kart) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(kart);
        session.getTransaction().commit();
    }

    @Override
    public List<Kart> getProductsByUserId(int userId) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        String query = "FROM Kart k where k.id.user.userId = :userId";
        Query kartQuery = session.createQuery(query, Kart.class);
        kartQuery.setParameter("userId", userId);
        List<Kart> kartList = kartQuery.getResultList();
        session.getTransaction().commit();
        return kartList;
    }

    public Kart findById(Kart.KartProductKey kartProductKey) {
        session = sessionFactory.openSession();
        return session.find(Kart.class, kartProductKey);
    }

    @Override
    public void update(Kart existingKartItem) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(existingKartItem);
        session.getTransaction().commit();
    }
}

