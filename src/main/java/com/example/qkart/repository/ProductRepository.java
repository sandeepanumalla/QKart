package com.example.qkart.repository;

import com.example.qkart.model.Product;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class ProductRepository implements IProductRepository{

    private final Session session;

    public ProductRepository(Session session) {
        this.session = session;
    }

    @Override
    public void save(Product product) {
        session.persist(product);
    }

    @Override
    public List<Product> getAllProducts() {
        session.beginTransaction();
        String query = "FROM Product";
        List<Product> productList = session.createQuery(query, Product.class).getResultList();
        session.getTransaction().commit();
        session.get(Product.class, 1);
        return productList;
    }

    @Override
    public Optional<Product> getProductById() {
        return Optional.empty();
    }
}
