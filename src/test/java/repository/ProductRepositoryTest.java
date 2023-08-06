package repository;

import com.example.qkart.model.Product;
import com.example.qkart.repository.IProductRepository;
import com.example.qkart.repository.ProductRepository;
import com.example.qkart.repository.SessionProvider;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductRepositoryTest {

    private Session session;

    @Mock
    private SessionFactory sessionFactory;


    @Mock
    private Query query;

    @InjectMocks
    private IProductRepository productRepository;



    @BeforeEach
    public void setup() {
        session = SessionProvider.createSession();
        productRepository = new ProductRepository(SessionProvider.getSessionFactory());
    }


    @Test
    public void testGetAllProducts() {
        List<Product> expectedProducts = new ArrayList<>();
        Product product1 = Product.builder()
                .productId(1)
                .name("Product1")
                .price(10.0)
                .build();

        Product product2 = Product.builder()
                .productId(2)
                .name("Product2")
                .price(20.0)
                .build();

        expectedProducts.add(product1);
        expectedProducts.add(product2);

        List<Product> productList = productRepository.getAllProducts();

        assertEquals(product1.getProductId(), productList.get(0).getProductId());
        assertEquals(product2.getProductId(), productList.get(1).getProductId());

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
    public void findProductById() {
        int productId = 1;
        Optional<Product> product = productRepository.getProductById(productId);
        Assertions.assertTrue(product.isPresent());
        assertEquals(product.get().getProductId(), productId);
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
//        session.beginTransaction();
//        String query = "FROM Product";
//        List<Product> productList = session.createQuery(query, Product.class).getResultList();
//        session.getTransaction().commit();
//        session.get(Product.class, 1);
//        System.out.println(productList);
    }

}
