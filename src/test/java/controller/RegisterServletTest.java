package controller;


import com.example.qkart.controller.RegisterServlet;
import com.example.qkart.service.IUserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;


public class RegisterServletTest {

    @Mock
    private IUserService userService;

    @InjectMocks
    private RegisterServlet registerServlet;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher requestDispatcher;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDoPost_SuccessfulRegistration() throws Exception {

        when(request.getParameter("username")).thenReturn("testUser");
        when(request.getParameter("firstname")).thenReturn("Test");
        when(request.getParameter("password")).thenReturn("password");
        when(request.getParameter("confirmPassword")).thenReturn("password");
        when(request.getRequestDispatcher("/Login.jsp")).thenReturn(requestDispatcher);

        // Mock successful registration
        doNothing().when(userService).register(any());

        registerServlet.doPost(request, response);

        verify(request, times(1)).getRequestDispatcher("/Login.jsp");
    }
}
