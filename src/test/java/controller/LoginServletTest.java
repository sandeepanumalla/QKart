package controller;

import com.example.qkart.config.AppConfig;
import com.example.qkart.controller.LoginServlet;
import com.example.qkart.dao.UserLoginRequest;
import com.example.qkart.service.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class LoginServletTest {

//    @Mock
//    private AppConfig appConfig;

    @Mock
    private IUserService userService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Mock
    private HttpSession session;

    @InjectMocks
    private LoginServlet loginServlet;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDoPost_SuccessfulLogin() throws Exception {
        String username = "sanumalla2";
        String password = "Test123";

        when(request.getParameter("username")).thenReturn(username);
        when(request.getParameter("password")).thenReturn(password);
        when(request.getSession()).thenReturn(session);

        UserLoginRequest userLoginRequest = new UserLoginRequest(username, password);
        doNothing().when(userService).login(userLoginRequest);

        loginServlet.doPost(request, response);

        verify(session).setAttribute("username", username);
        verify(response).sendRedirect("Products.jsp");
    }


    @Test
    public void testDoPost_FailedLogin() throws Exception {
        String username = "testuser";
        String password = "wrongpassword";

        when(request.getParameter("username")).thenReturn(username);
        when(request.getParameter("password")).thenReturn(password);
        when(request.getContextPath()).thenReturn("/qkart");

        UserLoginRequest userLoginRequest = new UserLoginRequest(username, password);
        doNothing().when(userService).login(userLoginRequest);

        doThrow(new RuntimeException("Login failed")).when(userService).login(userLoginRequest);

        loginServlet.doPost(request, response);

        verify(response).sendRedirect("/qkart/Login.jsp?error=1");
    }
}
