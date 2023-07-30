package com.example.qkart.repository;

import com.example.qkart.model.Kart;
import com.example.qkart.model.Product;
import jakarta.persistence.Query;
import org.hibernate.Session;

import java.util.List;

public class KartRepository implements IKartRepository{

    private final Session session;

    public KartRepository(Session session) {
        this.session = session;
    }

    @Override
    public void save(Kart kart) {
        session.persist(kart);
    }

    @Override
    public List<Kart> getProductsByUserId(int userId) {
        session.beginTransaction();
        String query = "FROM Kart k where k.id.user.userId = :userId";
        Query kartQuery = session.createQuery(query, Kart.class);
        kartQuery.setParameter("userId", userId);
        List<Kart> kartList = kartQuery.getResultList();
        session.getTransaction().commit();
        return kartList;
    }

    public Kart findById(Kart.KartProductKey kartProductKey) {
         return session.find(Kart.class, kartProductKey);
    }
}

