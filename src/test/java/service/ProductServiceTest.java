package service;

import com.example.qkart.model.Product;
import com.example.qkart.repository.IProductRepository;
import com.example.qkart.service.IProductsService;
import com.example.qkart.service.ProductsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class ProductServiceTest {

    @Mock
    private IProductRepository productRepository;

    @InjectMocks
    private ProductsService productsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllProducts() {
        List<Product> expectedProducts = new ArrayList<>();
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

        expectedProducts.add(product1);
        expectedProducts.add(product2);

        when(productRepository.getAllProducts()).thenReturn(expectedProducts);

        List<Product> actualProducts = productsService.getAllProductsWithFilterApplied();

        verify(productRepository).getAllProducts();

        assertEquals(2, actualProducts.size());
    }
}
