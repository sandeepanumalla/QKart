package repository;

import com.example.qkart.model.Kart;
import com.example.qkart.model.Product;
import com.example.qkart.model.User;
import com.example.qkart.repository.IKartRepository;
import com.example.qkart.repository.KartRepository;
import com.example.qkart.repository.SessionProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KartRepositoryTest {

    private Session session;
    private IKartRepository kartRepository;


    @BeforeEach
    public void setup() {
        session = SessionProvider.createSession();
        this.kartRepository = new KartRepository(session);
    }

    @Test
    public void saveTest() {
        Transaction transaction = session.beginTransaction();
        Product product = Product.builder()
                .productId(2)
                .description("The Minimalist Slim Leather Watch")
                .imageURL("www.minimalist.com")
                .name("Slim Leather Watch")
                .price(60)
                .category(null)
                .build();

        User user = User.builder()
                .userId(20)
                .firstName("")
                .build();

        Kart.KartProductKey kartProductKey = Kart.KartProductKey.builder()
                .product(product)
                .user(user)
                .build();

        Kart kart = Kart
                .builder()
                .id(kartProductKey)
                .quantity(1)
                .build();

        session.persist(kart);

        transaction.commit();
        session.close();
    }

    @Test
    public void getAllProducts() {
        session.beginTransaction();
        String query = "FROM Product";
        List<Product> productList = session.createQuery(query, Product.class).getResultList();
        session.getTransaction().commit();
        session.get(Product.class, 1);
        System.out.println(productList);
    }

    @Test
    public void testGetProductsByUserId() {
        int userId = 20;

        // Mock the Query


        // Mock the result list
        List<Kart> expectedKartList = new ArrayList<>();
        // Add some Kart objects to the list as per the test case
        expectedKartList.add(new Kart(/* Add relevant constructor parameters here */));


        List<Kart> actualKartList = kartRepository.getProductsByUserId(userId);

        System.out.println(actualKartList);
    }

    @Test
    public void testFindById_WhenKartExists_ShouldReturnKart() {
        // Given
        int userId = 20;
        List<Kart> actualKartList = kartRepository.getProductsByUserId(userId);

        // When
        Kart resultKart = kartRepository.findById(actualKartList.get(0).getId());

        // Then
        assertEquals(actualKartList.get(0), resultKart);
    }
}


