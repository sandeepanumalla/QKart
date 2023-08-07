package service;

import com.example.qkart.dao.UserLoginRequest;
import com.example.qkart.dao.UserRegisterRequest;
import com.example.qkart.model.Cart;
import com.example.qkart.model.User;
import com.example.qkart.repository.*;
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

    private ICartRepository cartRepository;

    @BeforeEach
    void setup() {
        this.sessionFactory = SessionProvider.getSessionFactory();
        this.userRepository = new UserRepository(sessionFactory);
        this.modelMapper = new ModelMapper();
        this.cartRepository = new CartRepository(sessionFactory);
        userService = new UserService(userRepository, cartRepository, modelMapper, Validation.buildDefaultValidatorFactory().getValidator());
    }

    @Test
    public void shouldUserAbleToRegister() {
        try {
            Cart cart = Cart.builder()
                    .build();

            User user = User.builder()
                    .cart(cart)
                    .dateCreated(new Date())
                    .firstName("Sandeep")
                    .username("sanumalla2")
                    .password("Test123")
                    .build();

            cart.setUser(user);

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
        UserLoginRequest loginRequest = new UserLoginRequest("valid_username", "incorrect_password");

        assertThrows(Exception.class, () -> userService.login(loginRequest));
    }
}
