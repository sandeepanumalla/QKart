package com.example.qkart.service;

import com.example.qkart.model.Kart;
import com.example.qkart.model.Product;
import com.example.qkart.model.User;
import com.example.qkart.repository.IKartRepository;
import com.example.qkart.repository.IUserRepository;
import com.example.qkart.repository.KartRepository;


import java.util.List;
import java.util.Map;

public class KartService implements IKartService{

    private final IKartRepository kartRepository;
    private final IUserRepository userRepository;

    public KartService(IKartRepository kartRepository, IUserRepository userRepository) {
        this.kartRepository = kartRepository;
        this.userRepository = userRepository;
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

            User user = userRepository.findById(userId);
            Kart.KartProductKey kartProductKey = new Kart.KartProductKey(user, product);
            Kart existingKartItem = kartRepository.findById(kartProductKey);

            if(existingKartItem != null) {
                int currentQuantity = existingKartItem.getQuantity();
                existingKartItem.setQuantity(currentQuantity + quantityToAdd);
            } else {
                Kart newKartItem = Kart.builder()
                        .id(kartProductKey)
                        .quantity(quantityToAdd)
                        .build();
                kartRepository.save(newKartItem);
            }
        }
    }

    @Override
    public int getProductCountByUser() {
        return 0;
    }
}
