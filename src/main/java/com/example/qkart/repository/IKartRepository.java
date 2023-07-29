package com.example.qkart.repository;

import com.example.qkart.model.Kart;

import java.util.List;


public interface IKartRepository {

    void save(Kart kart);

    List<Kart> getProductsByUserId(int userId);

}
