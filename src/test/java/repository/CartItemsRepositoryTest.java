package repository;

import com.example.qkart.model.CartItems;
import com.example.qkart.repository.CartItemsRepository;
import com.example.qkart.repository.ICartRepository;
import com.example.qkart.repository.SessionProvider;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CartItemsRepositoryTest {


    private SessionFactory sessionFactory;

    private Session session;

    private Query<CartItems> query;

    private CartItemsRepository cartItemsRepository;


    @BeforeEach
    void setUp() {
        sessionFactory = SessionProvider.getSessionFactory();
        cartItemsRepository = new CartItemsRepository(sessionFactory);
    }

    @Test
    void testGetCartItems() throws Exception {
        int cartId = 1;
        List<CartItems> cartItemsList = cartItemsRepository.getCartItems(cartId);
        System.out.println(cartItemsList);
    }

    void testSaveCartItem() {
        int cartId;

    }

}
