package com.example.qkart.repository;

import com.example.qkart.model.CartItems;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CartItemsRepository implements ICartItemsRepository{

    private final SessionFactory sessionFactory;
    private Session session;

    public CartItemsRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<CartItems> findByCart(int cartId) {
        try(Session session = sessionFactory.openSession()) {
            String hql = "FROM CartItems ci WHERE ci.cart.id = :cartId";
            Query<CartItems> query = session.createQuery(hql, CartItems.class);
            query.setParameter("cartId", cartId);
            return query.getResultList();
        } catch (Exception exception) {
            return null;
        }
    }

    @Override
    public void removeById(int cartItemsId) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(cartItemsId);
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveCartItem(CartItems cartItems) {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(cartItems);
            transaction.commit();;
        }
    }

}
