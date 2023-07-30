package com.example.qkart.service;

import com.example.qkart.dao.AddItemsRequest;
import com.example.qkart.model.Kart;
import com.example.qkart.model.Product;
import com.example.qkart.repository.IKartRepository;
import com.example.qkart.repository.KartRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import java.util.List;
import java.util.Map;

public class KartService implements IKartService{

    private final IKartRepository kartRepository;

    public KartService(IKartRepository kartRepository) {
        this.kartRepository = kartRepository;
    }

//    @Override
//    public List<Kart> getKartItemsForUser(AddItemsRequest addItemsRequest) {
//
//        List<Kart> kartList = kartRepository.getProductsByUserId(addItemsRequest.userId);
//
////        kartList.stream().forEach(e ->{});
//
//        return null;
//    }

    @Override
    public void addItemToCart(int userId, Map<Product, Integer> productsWithQuantities) {
        if(productsWithQuantities == null || productsWithQuantities.isEmpty()) {
            throw new IllegalArgumentException("Invalid products with quantities to add.");
        }

        List<Kart> kart = kartRepository.getProductsByUserId(userId);

        for(Map.Entry<Product, Integer> entry: productsWithQuantities.entrySet()) {
            Product product = entry.getKey();
            int quantityToAdd = entry.getValue();

            if(product == null || quantityToAdd <= 0) {
                throw new IllegalArgumentException("Invalid products with quantities to add.");
            }

            Kart.KartProductKey kartProductKey = new Kart.KartProductKey(null, product);


        }
    }

    @Override
    public int getProductCountByUser() {
        return 0;
    }
}
