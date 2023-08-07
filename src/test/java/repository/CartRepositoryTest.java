package repository;

import com.example.qkart.model.Cart;
import com.example.qkart.model.CartItems;
import com.example.qkart.repository.CartItemsRepository;
import com.example.qkart.repository.ICartRepository;
import com.example.qkart.repository.CartRepository;
import com.example.qkart.repository.SessionProvider;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class CartRepositoryTest {

    private SessionFactory sessionFactory;
    private ICartRepository kartRepository;


    @Mock
    private SessionFactory mockedsessionFactory;

    @Mock
    private Session session;

    @Mock
    private Transaction transaction;


    private CartItemsRepository cartItemsRepository;

    @BeforeEach
    public void setup() {
        sessionFactory = SessionProvider.getSessionFactory();
        this.kartRepository = new CartRepository(sessionFactory);
        this.cartItemsRepository = new CartItemsRepository(sessionFactory);
    }

    @Test
    public void saveTest() {

    }


    @Test
    void shouldRemoveCartItemById() {
        // Arrange
        int cartItemsId = 1;


    }
    @Test
    public void getAllProducts() {

    }

    @Test
    public void testGetProductsByUserId() {

    }

    @Test
    public void testFindById_WhenKartExists_ShouldReturnKart() {

    }
}


