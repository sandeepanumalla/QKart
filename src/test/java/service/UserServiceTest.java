package service;

import com.example.qkart.dao.UserLoginRequest;
import com.example.qkart.dao.UserRegisterRequest;
import com.example.qkart.model.User;
import com.example.qkart.repository.IUserRepository;
import com.example.qkart.repository.SessionProvider;
import com.example.qkart.repository.UserRepository;
import com.example.qkart.service.IUserService;
import com.example.qkart.service.UserService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserServiceTest {
    private IUserService userService;
    private IUserRepository userRepository;

    private SessionFactory sessionFactory;
    private ModelMapper modelMapper;

    @BeforeEach
    void setup() {
        this.sessionFactory = SessionProvider.getSessionFactory();
        this.userRepository = new UserRepository(sessionFactory);
        this.modelMapper = new ModelMapper();
        userService = new UserService(userRepository, modelMapper, Validation.buildDefaultValidatorFactory().getValidator());
    }

    @Test
    public void shouldUserAbleToRegister() {
        try {
            User user = User.builder()
                    .cart(null)
                    .dateCreated(new Date())
                    .firstName("Sandeep")
                    .username("sanumalla2")
                    .password("Test123")
                    .build();

            UserRegisterRequest userRegisterRequest = modelMapper.map(user, UserRegisterRequest.class);

            userService.register(userRegisterRequest);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void shouldThrowValidationErrorIfEmptyFields() {
        User user = User.builder()
                .cart(null)
                .dateCreated(new Date())
                .firstName("Sandeep")
                .username("sanumakla")
                .build();

        UserRegisterRequest userRegisterRequest = modelMapper.map(user, UserRegisterRequest.class);

        Assertions.assertThrows(ConstraintViolationException.class, () -> userService.register(userRegisterRequest));
    }

    @Test
    public void testSuccessfulLogin() {

        UserLoginRequest loginRequest = new UserLoginRequest("sanumalla2", "Test123");
        assertDoesNotThrow(() -> userService.login(loginRequest));
    }

    @Test
    public void testLoginWithIncorrectPassword() {
        // Mock UserLoginRequest with valid data
        UserLoginRequest loginRequest = new UserLoginRequest("valid_username", "incorrect_password");

        assertThrows(Exception.class, () -> userService.login(loginRequest));
    }
}
