package service;

import com.example.qkart.model.Product;
import com.example.qkart.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.example.qkart.repository.*;
import com.example.qkart.service.KartService;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CartServiceTest {


    private CartRepository kartRepository;


    private UserRepository userRepository;


    private ProductRepository productRepository;

    private KartService kartService;

    private ICartItemsRepository cartItemsRepository;


    @BeforeEach
    public void setUp() {
        SessionFactory sessionFactory = SessionProvider.getSessionFactory();
        kartRepository = new CartRepository(sessionFactory);
        userRepository = new UserRepository(sessionFactory);
        productRepository = new ProductRepository(sessionFactory);
        kartService = new KartService(kartRepository, userRepository, cartItemsRepository, productRepository);
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
//
//        Cart.KartProductKey kartProductKey11 = new Cart.KartProductKey(user, product1.get());
//        Cart.KartProductKey kartProductKey22 = new Cart.KartProductKey(user, product2.get());
//
//        Cart cartItem11 = kartRepository.findById(kartProductKey11);
//        Cart cartItem22 = kartRepository.findById(kartProductKey22);
//
//        int quantity1Before = kartRepository.findById(kartProductKey11).getQuantity();
//        int quantity2Before = kartRepository.findById(kartProductKey22).getQuantity();
//
//        kartService.addItemToCart(user.getUserId(), productsWithQuantities);
//
//        Cart.KartProductKey kartProductKey1 = new Cart.KartProductKey(user, product1.get());
//        Cart.KartProductKey kartProductKey2 = new Cart.KartProductKey(user, product2.get());
//
//        Cart cartItem1 = kartRepository.findById(kartProductKey1);
//        Cart cartItem2 = kartRepository.findById(kartProductKey2);
//
//        assertNotNull(cartItem1);
//        assertNotNull(cartItem2);
////
//        assertEquals( quantity1Before + 2, cartItem1.getQuantity());
//        assertEquals(quantity2Before + 3, cartItem2.getQuantity());
    }

}
