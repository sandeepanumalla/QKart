package controller;

import com.example.qkart.controller.LogoutServlet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;

public class LogoutServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private RequestDispatcher requestDispatcher;

    @InjectMocks
    private LogoutServlet logoutServlet;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDoGet_LogsOutUser() throws Exception {
        when(request.getSession(false)).thenReturn(session);

        logoutServlet.doGet(request, response);

        verify(session).invalidate();
        verify(response).sendRedirect("Login.jsp");
    }

    @Test
    public void testDoPost_CallsDoGet() throws Exception {
        logoutServlet.doPost(request, response);

        verify(logoutServlet).doGet(request, response);
    }
}
