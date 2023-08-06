package com.example.qkart.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public interface ICategoryRepository {


    void save(String categoryName);
}
