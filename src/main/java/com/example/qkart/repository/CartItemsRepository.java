package com.example.qkart.repository;

import com.example.qkart.model.Cart;
import com.example.qkart.model.CartItems;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CartItemsRepository implements ICartItemsRepository{

    public SessionFactory sessionFactory;
    public Session session;


    public CartItemsRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }



    @Override
    public void removeById(int cartItemsId) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            CartItems cartItem = session.get(CartItems.class, cartItemsId); // Fetch the entity by ID
            if (cartItem != null) {
                session.remove(cartItem); // Remove the entity
            }
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveCartItem(CartItems cartItems) {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            session.merge(cartItems);
            transaction.commit();;
        }
    }

    @Override
    public List<CartItems> getCartItems(int cartId) throws Exception {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String hql = "FROM CartItems ci where ci.cart.id = :cartId";
            Query<CartItems> query = session.createQuery(hql, CartItems.class);
            query.setParameter("cartId", cartId); // Make sure to set the parameter
            return query.getResultList();
        }
    }

    @Override
    public void removeByCart(int cartId) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            String query = "DELETE FROM CartItems ci where ci.cart.id = :cartId";
            Query<?> cartItemsQuery = session.createQuery(query);
            cartItemsQuery.setParameter("cartId", cartId);

            int rowsDeleted = cartItemsQuery.executeUpdate(); // Execute the DELETE query

            session.getTransaction().commit();

            System.out.println("Deleted " + rowsDeleted + " cart items.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CartItems getCartItemByProductId(int productId) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String query = "FROM CartItems ci where ci.product.id = :productId";
            Query<CartItems> cartItemsQuery = session.createQuery(query, CartItems.class);
            cartItemsQuery.setParameter("productId", productId);
            List<CartItems> cartItemsList = cartItemsQuery.getResultList();
            return cartItemsList.get(0);
        }
    }
}
