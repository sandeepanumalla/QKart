package repository;

import com.example.qkart.model.Category;
import com.example.qkart.repository.CategoryRepository;
import com.example.qkart.repository.SessionProvider;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class CategoryRepositoryTest {

        private CategoryRepository categoryRepository;
        private SessionFactory sessionFactory;
        private Session session;

        @BeforeEach
        public void setUp() {
            sessionFactory = SessionProvider.getSessionFactory();
            session = sessionFactory.openSession();
            categoryRepository = new CategoryRepository(sessionFactory);
        }

        @Test
        public void testSaveCategory() {
            // Given
            String categoryName = "men's clothing";
            String categoryName2 = "jewelery";
            String categoryName3 = "electronics";
            String categoryName4 = "women's clothing";

            // When
            categoryRepository.save(categoryName);
            categoryRepository.save(categoryName2);
            categoryRepository.save(categoryName3);
            categoryRepository.save(categoryName4);

        }
    }


