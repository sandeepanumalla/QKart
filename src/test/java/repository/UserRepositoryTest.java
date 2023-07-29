package repository;

import com.example.qkart.model.User;
import com.example.qkart.repository.SessionProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class UserRepositoryTest {

    private Session session;

    @BeforeEach
    public void setup() {
        session = SessionProvider.createSession();
    }

    @Test
    public void saveTest() {
        Transaction transaction = session.beginTransaction();
        User user = User.builder()
                .kart(null)
                .dateCreated(new Date())
                .firstName("Sandeep")
                .lastName("Anumalla")
                .username("sanumalla")
                .build();

        session.persist(user);
        transaction.commit();
        session.close();
        System.out.println("new user has been successfully registered");
    }

//    @Test
//    public void getAllProducts() {
//        session.beginTransaction();
//        String query = "FROM Product";
//        List<Product> productList = session.createQuery(query, Product.class).getResultList();
//        session.getTransaction().commit();
//        session.get(Product.class, 1);
//        System.out.println(productList);
//    }
}
