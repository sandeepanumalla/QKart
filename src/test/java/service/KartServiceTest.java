package service;

import com.example.qkart.model.Kart;
import com.example.qkart.model.Product;
import com.example.qkart.model.User;

import java.util.Map;

import com.example.qkart.model.*;
import com.example.qkart.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class KartServiceTest {


    private KartRepository kartRepository;


    private UserRepository userRepository;


    private ProductRepository productRepository;

    private KartService kartService;

    @BeforeEach
    public void setUp() {
        kartService = new KartService(kartRepository, userRepository);
    }

    @Test
    public void testAddItemToCart_WhenValidData_ShouldAddToCart() {
        // Given
        User user = createUser();
        Product product1 = createProduct();
        Product product2 = createProduct();

        userRepository.save(user);
        productRepository.save(product1);
        productRepository.save(product2);

        Map<Product, Integer> productsWithQuantities = new HashMap<>();
        productsWithQuantities.put(product1, 2);
        productsWithQuantities.put(product2, 3);

        // When
        kartService.addItemToCart(user.getUserId(), productsWithQuantities);

        // Then
        Kart.KartProductKey kartProductKey1 = new Kart.KartProductKey(user, product1);
        Kart.KartProductKey kartProductKey2 = new Kart.KartProductKey(user, product2);

        Kart kartItem1 = kartRepository.findById(kartProductKey1);
        Kart kartItem2 = kartRepository.findById(kartProductKey2);

        assertNotNull(kartItem1);
        assertNotNull(kartItem2);

        assertEquals(2, kartItem1.getQuantity());
        assertEquals(3, kartItem2.getQuantity());
    }

    private User createUser() {
        // Create and return a test User
    }

    private Product createProduct() {
        // Create and return a test Product
    }
}
