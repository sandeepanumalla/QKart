package repository;

import com.example.qkart.model.Kart;
import com.example.qkart.model.Product;
import com.example.qkart.model.User;
import com.example.qkart.repository.SessionProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class KartRepositoryTest {

    private Session session;

    @BeforeEach
    public void setup() {
        session = SessionProvider.createSession();
    }

    @Test
    public void saveTest() {
        Transaction transaction = session.beginTransaction();
        Product product = Product.builder()
                .productId(1)
                .description("The Minimalist Slim Leather Watch")
                .imageURL("www.minimalist.com")
                .name("Slim Leather Watch")
                .price(60)
                .category(null)
                .build();

        User user = User.builder()
                .userId(1)
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
}
