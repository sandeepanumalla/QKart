package controller;

import com.example.qkart.controller.ProductsServlet;
import com.example.qkart.model.Product;
import com.example.qkart.service.ProductsService;
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
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductServletTest {

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpServletRequest request;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Mock
    private ProductsService productsService;

    @InjectMocks
    private ProductsServlet productsServlet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        // Ensure that the requestDispatcher forwards correctly
//        when(request.getRequestDispatcher("/products")).thenReturn(requestDispatcher);
    }

    @Test
    void testListProducts() throws ServletException, IOException {
        List<Product> productList = new ArrayList<>();
        Product product1 = Product.builder()
                .productId(1)
                .name("Product1")
                .price(10.0)
                .build();

        Product product2 = Product.builder()
                .productId(2)
                .name("Product2")
                .price(20.0)
                .build();
        productList.add(product1);
        productList.add(product2);
        // Add more products if needed

        when(productsService.getAllProductsWithFilterApplied()).thenReturn(productList);
        when(request.getRequestDispatcher("/products")).thenReturn(requestDispatcher);

        // Call the method being tested
        productsServlet.listProducts(request, response);

        // Verify that the getAllProductsWithFilterApplied method was called
        verify(productsService).getAllProductsWithFilterApplied();

        // Verify that request attributes were set
        verify(request).setAttribute("productList", productList);

        verify(requestDispatcher).forward(request, response);

    }
}
