package com.example.qkart.repository;

import com.example.qkart.model.Cart;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class CartRepository implements ICartRepository {

    private final SessionFactory sessionFactory;
    private Session session;

    public CartRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Cart cart) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(cart);
        session.getTransaction().commit();
    }

    @Override
    public List<Cart> getProductsByUserId(int userId) {
        return null;
    }

    @Override
    public Cart findById(int cartId) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        Cart cart = session.get(Cart.class, cartId);
        session.getTransaction().commit();
        return cart;
    }





//    @Override
//    public Cart findById(Cart.KartProductKey kartProductKey) {
//        return null;
//    }

//    @Override
//    public List<Cart> getProductsByUserId(int userId) {
//        session = sessionFactory.openSession();
//        session.beginTransaction();
//        String query = "FROM Cart k where k.id.user.userId = :userId";
//        Query kartQuery = session.createQuery(query, Cart.class);
//        kartQuery.setParameter("userId", userId);
//        List<Cart> cartList = kartQuery.getResultList();
//        session.getTransaction().commit();
//        return cartList;
//    }

//    public Cart findById(Cart.KartProductKey kartProductKey) {
//        session = sessionFactory.openSession();
//        return session.find(Cart.class, kartProductKey);
//    }

    @Override
    public void update(Cart existingCartItem) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(existingCartItem);
        session.getTransaction().commit();
    }

    @Override
    public int getTotalPrice(int cartId) {
        return 0;
    }

    @Override
    public int getCartSize(int cartId) {
        return 0;
    }

}

