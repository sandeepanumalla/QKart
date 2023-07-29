package com.example.qkart.repository;

import com.example.qkart.model.Kart;
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
        String query = "FROM Kart";
        List<Kart> kartList = session.createQuery(query, Kart.class).getResultList();
        session.getTransaction().commit();
        return kartList;
    }
}
