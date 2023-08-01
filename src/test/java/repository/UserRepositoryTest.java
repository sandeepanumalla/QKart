package repository;

import com.example.qkart.model.User;
import com.example.qkart.repository.IUserRepository;
import com.example.qkart.repository.SessionProvider;
import com.example.qkart.repository.UserRepository;
import jakarta.persistence.PersistenceException;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class UserRepositoryTest {

    private SessionFactory sessionFactory;

    private IUserRepository userRepository;


    @BeforeEach
    public void setup() {
        sessionFactory = SessionProvider.getSessionFactory();
        userRepository = new UserRepository(sessionFactory);
    }

    @Test
    public void saveTest() {
        User user = User.builder()
                .kart(null)
                .dateCreated(new Date())
                .firstName("Sandeep")
                .username("sanumalla")
                .build();

        userRepository.save(user);

        System.out.println("new user has been successfully registered");
    }

    @Test
    public void saveTestShouldThrowUniqueConstraintViolatonException() {
        User user = User.builder()
                .kart(null)
                .dateCreated(new Date())
                .firstName("Sandeep")
                .username("sanumalla")
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> userRepository.save(user));
        System.out.println("new user has been successfully registered");
    }



    @Test
    public void findUserByUsernameTest() {
        String username = "sanumalla";
        User user = User.builder()
                .kart(null)
                .dateCreated(new Date())
                .firstName("Sandeep")
                .username("sanumalla")
                .build();

        Optional<User> result = userRepository.finduserByUsername(username);

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(user.getUsername(), result.get().getUsername());
    }


    @Test
    public void findUserByUsernameTestNoUserFound() {
        String username = "nonexistent";
        List<User> users = new ArrayList<>();


        Optional<User> result = userRepository.finduserByUsername(username);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void findByIdTest() {
        int userId = 20;
        User user = userRepository.findById(userId);
        Assertions.assertNotNull(user);
        Assertions.assertEquals(userId, user.getUserId());
    }
}
