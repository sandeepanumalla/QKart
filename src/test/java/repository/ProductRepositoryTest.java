package repository;

import com.example.qkart.model.Product;
import com.example.qkart.repository.SessionProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

public class ProductRepositoryTest {

    private Session session;

    @BeforeEach
    public void setup() {
        session = SessionProvider.createSession();
    }

    @Test
    public void saveTest() {
        Transaction transaction = session.beginTransaction();
        Product product = Product.builder()
                .description("The Minimalist Slim Leather Watch")
                .imageURL("www.minimalist.com")
                .name("Slim Leather Watch")
                .price(60)
                .category(null)
                .build();
        session.persist(product);

        transaction.commit();
        session.close();
    }

    @Test
    public void saveMultipleProducesTest() {
        Transaction transaction = session.beginTransaction();

        for (int i = 1; i <= 10; i++) {
            Product product = Product.builder()
                    .description("Product " + i + " Description")
                    .imageURL("www.product" + i + ".com/image.jpg")
                    .name("Product " + i)
                    .price(10 + i) // Just an example, you can set the actual prices for each product.
                    .category(null) // Set the actual category if available.
                    .build();
            session.persist(product);
        }

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
