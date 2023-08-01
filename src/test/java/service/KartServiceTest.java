package service;

import com.example.qkart.model.Kart;
import com.example.qkart.model.Product;
import com.example.qkart.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.example.qkart.model.*;
import com.example.qkart.repository.*;
import com.example.qkart.service.KartService;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class KartServiceTest {


    private KartRepository kartRepository;


    private UserRepository userRepository;


    private ProductRepository productRepository;

    private KartService kartService;

    @BeforeEach
    public void setUp() {
        SessionFactory sessionFactory = SessionProvider.getSessionFactory();
        kartRepository = new KartRepository(sessionFactory);
        userRepository = new UserRepository(sessionFactory);
        productRepository = new ProductRepository(sessionFactory);
        kartService = new KartService(kartRepository, userRepository);
    }

    @Test
    public void testAddItemToCart_WhenValidData_ShouldAddToCart() {
        // Given

        int userId = 20;
        int productId = 1;
        int productId2 = 2;
        User user = userRepository.findById(userId);
        Optional<Product> product1 = productRepository.getProductById(productId);
        Optional<Product> product2 = productRepository.getProductById(productId2);

        Map<Product, Integer> productsWithQuantities = new HashMap<>();
        productsWithQuantities.put(product1.get(), 2);
        productsWithQuantities.put(product2.get(), 3);

        Kart.KartProductKey kartProductKey11 = new Kart.KartProductKey(user, product1.get());
        Kart.KartProductKey kartProductKey22 = new Kart.KartProductKey(user, product2.get());

        Kart kartItem11 = kartRepository.findById(kartProductKey11);
        Kart kartItem22 = kartRepository.findById(kartProductKey22);

        int quantity1Before = kartRepository.findById(kartProductKey11).getQuantity();
        int quantity2Before = kartRepository.findById(kartProductKey22).getQuantity();

        kartService.addItemToCart(user.getUserId(), productsWithQuantities);

        Kart.KartProductKey kartProductKey1 = new Kart.KartProductKey(user, product1.get());
        Kart.KartProductKey kartProductKey2 = new Kart.KartProductKey(user, product2.get());

        Kart kartItem1 = kartRepository.findById(kartProductKey1);
        Kart kartItem2 = kartRepository.findById(kartProductKey2);
//
        assertNotNull(kartItem1);
        assertNotNull(kartItem2);
//
        assertEquals( quantity1Before + 2, kartItem1.getQuantity());
        assertEquals(quantity2Before + 3, kartItem2.getQuantity());
    }

}
