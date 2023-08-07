package com.example.qkart.repository;

import com.example.qkart.model.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CategoryRepository implements ICategoryRepository{

    private final SessionFactory sessionFactory;
    private Session session;

    public CategoryRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void save(String categoryName) {
        try(Session session = sessionFactory.openSession()) {
          session.beginTransaction();
            Category category = Category.builder()
                    .categoryName(categoryName)
                    .build();
          session.persist(category);
          session.getTransaction().commit();
        }
    }
}
