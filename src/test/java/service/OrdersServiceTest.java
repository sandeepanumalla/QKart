package service;


import com.example.qkart.model.CartItems;
import com.example.qkart.model.Orders;
import com.example.qkart.repository.*;
import com.example.qkart.service.CartItemsService;
import com.example.qkart.service.ICartItemsService;
import com.example.qkart.service.IOrderService;
import com.example.qkart.service.OrderService;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import java.util.ArrayList;

public class OrdersServiceTest {

    private IOrderService orderService;

    private ICartItemsRepository cartItemsRepository;

    private ICartItemsService cartItemsService;

    private IProductRepository productRepository;

    private ICartRepository cartRepository;

    private IOrderRepository orderRepository;

    private SessionFactory sessionFactory;



    @BeforeEach
    void setUp() {
        this.sessionFactory = SessionProvider.getSessionFactory();
        this.cartRepository = new CartRepository(sessionFactory);
        this.cartItemsRepository = new CartItemsRepository(sessionFactory);
        this.orderRepository = new OrderRepository(sessionFactory);
        this.productRepository = new ProductRepository(sessionFactory);
        this.cartItemsService = new CartItemsService(cartRepository,cartItemsRepository, productRepository);
        this.orderService = new OrderService(orderRepository,cartItemsService);
    }

    @Test
    void testCreateOrderWithCartCheckout() {
        int productId1 = 1;
        int productId2 = 2;

        CartItems cartItems1 = cartItemsRepository.getCartItemByProductId(productId1);
        CartItems cartItems2 = cartItemsRepository.getCartItemByProductId(productId2);

        List<CartItems> cartItemsList = new ArrayList<>();
        cartItemsList.add(cartItems1);
        cartItemsList.add(cartItems2);

        orderService.createOrderWithCartCheckout(cartItemsList);
    }


    @Test
    void testCreateOrderWhenCartItemsDontExist() throws Exception {
        int productId1 = 1;
        int cartId = 1;
        int quantity = 1;

        orderService.createOrderWhenCartItemsDontExist(cartId ,productId1, quantity);
    }

    @Test
    void testCreateOrderWithSingleCartItem() {
        int productId1 = 2;

        CartItems cartItems1 = cartItemsRepository.getCartItemByProductId(productId1);

        orderService.createOrderWithSingleCartItem(cartItems1);
    }

    @Test
    void testGetAllOrders() {
        int userId = 1;
        List<Orders> ordersList = orderService.getOrdersByUser(userId);
        System.out.println(ordersList);
    }

    @Test
    void testCancelOrder() {
        int orderId = 24;
        orderService.cancelOrderById(orderId);
    }
}
