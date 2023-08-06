package repository;

import com.example.qkart.model.Cart;
import com.example.qkart.model.Product;
import com.example.qkart.model.User;
import com.example.qkart.repository.ICartRepository;
import com.example.qkart.repository.CartRepository;
import com.example.qkart.repository.SessionProvider;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartRepositoryTest {

    private SessionFactory sessionFactory;
    private ICartRepository kartRepository;


    @BeforeEach
    public void setup() {
        sessionFactory = SessionProvider.getSessionFactory();
        this.kartRepository = new CartRepository(sessionFactory);
    }
//
//    @Test
//    public void saveTest() {
////        Transaction transaction = sessionFactory.openSession().beginTransaction();
//        Product product = Product.builder()
//                .productId(2)
//                .description("The Minimalist Slim Leather Watch")
//                .imageURL("www.minimalist.com")
//                .name("Slim Leather Watch")
//                .price(60)
//                .category(null)
//                .build();
//
//        User user = User.builder()
//                .userId(20)
//                .firstName("")
//                .build();
//
//        Cart.KartProductKey kartProductKey = Cart.KartProductKey.builder()
//                .product(product)
//                .user(user)
//                .build();
//
//        Cart cart = Cart
//                .builder()
//                .id(kartProductKey)
//                .quantity(1)
//                .build();
//
//
//        kartRepository.save(cart);
////        session.persist(cart);
////
////        transaction.commit();
////        session.close();
//    }

    @Test
    public void getAllProducts() {
//        session.beginTransaction();
//        String query = "FROM Product";
//        List<Product> productList = session.createQuery(query, Product.class).getResultList();
//        session.getTransaction().commit();
//        session.get(Product.class, 1);
//        kartRepository.ge
//        System.out.println(productList);
    }

    @Test
    public void testGetProductsByUserId() {
        int userId = 20;

        // Mock the Query


        // Mock the result list
        List<Cart> expectedCartList = new ArrayList<>();
        // Add some Cart objects to the list as per the test case
        expectedCartList.add(new Cart(/* Add relevant constructor parameters here */));


        List<Cart> actualCartList = kartRepository.getProductsByUserId(userId);

        System.out.println(actualCartList);
    }

    @Test
    public void testFindById_WhenKartExists_ShouldReturnKart() {
        // Given
        int userId = 20;
        List<Cart> actualCartList = kartRepository.getProductsByUserId(userId);

        // When
//        Cart resultCart = kartRepository.findById(actualCartList.get(0).getId());

        // Then
//        assertEquals(actualCartList.get(0), resultCart);
    }
}


