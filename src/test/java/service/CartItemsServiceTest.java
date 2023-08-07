package service;

import com.example.qkart.model.Cart;
import com.example.qkart.model.CartItems;
import com.example.qkart.model.Product;
import com.example.qkart.repository.*;
import com.example.qkart.service.CartItemsService;
import com.example.qkart.service.ICartItemsService;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartItemsServiceTest {

    @Mock
    private ICartItemsRepository cartItemsRepository;

    @Mock
    private ICartRepository cartRepository;

    @Mock
    private IProductRepository productRepository;

    private CartItemsService cartItemsService;

    private CartItemsService unMockedCartItemsService;

    private ICartItemsRepository unMockedcartItemsRepository;


    private ICartRepository unMockedcartRepository;


    private IProductRepository unMockedproductRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        SessionFactory sessionFactory = SessionProvider.getSessionFactory();
        this.unMockedproductRepository = new ProductRepository(sessionFactory);
        this.unMockedcartRepository = new CartRepository(sessionFactory);
        this.unMockedcartItemsRepository = new CartItemsRepository(sessionFactory);
        this.unMockedCartItemsService = new CartItemsService(unMockedcartRepository, unMockedcartItemsRepository, unMockedproductRepository);
        cartItemsService = new CartItemsService(cartRepository, cartItemsRepository, productRepository);

    }

    @Test
    void testAddCartItem_ExistingProduct_Mocked() throws Exception {
        int cartId = 1;
        int productId = 2;
        int newQuantity = 5;

        Cart cart = new Cart();
        Product existingProduct = new Product();
        existingProduct.setProductId(productId);

        List<CartItems> cartItemsList = new ArrayList<>();
        CartItems existingCartItem = new CartItems();
        existingCartItem.setProduct(existingProduct);
        cartItemsList.add(existingCartItem);

        when(cartRepository.findById(anyInt())).thenReturn(cart);
        when(cartItemsService.getCartItems(anyInt())).thenReturn(cartItemsList);
        when(productRepository.getProductById(anyInt())).thenReturn(Optional.of(existingProduct));

        cartItemsService.addCartItem(cartId, productId, newQuantity);

        verify(cartItemsRepository, times(1)).saveCartItem(existingCartItem);
    }



    @Test
    void testAddCartItem_ExistingProduct() throws Exception {
        int cartId = 1;
        int productId = 2;
        int newQuantity = 5;

        Cart cart = new Cart();
        Product existingProduct = new Product();
        existingProduct.setProductId(productId);

        List<CartItems> cartItemsList = new ArrayList<>();
        CartItems existingCartItem = new CartItems();
        existingCartItem.setProduct(existingProduct);
        cartItemsList.add(existingCartItem);


        unMockedCartItemsService.addCartItem(cartId, productId, newQuantity);

    }

    @Test
    void testAddCartItem_NewProduct_Mocked() throws Exception {
        int cartId = 1;
        int productId = 2;
        int newQuantity = 5;

        Cart cart = new Cart();
        Product newProduct = new Product();
        newProduct.setProductId(productId);

        List<CartItems> cartItemsList = new ArrayList<>();

        when(cartRepository.findById(anyInt())).thenReturn(cart);
        when(cartItemsRepository.getCartItems(anyInt())).thenReturn(cartItemsList);
        when(productRepository.getProductById(anyInt())).thenReturn(Optional.of(newProduct));
        doNothing().when(cartItemsRepository).saveCartItem(any(CartItems.class));

        cartItemsService.addCartItem(cartId, productId, newQuantity);

        verify(cartItemsRepository, times(1)).saveCartItem(argThat(cartItem -> cartItem.getProduct().equals(newProduct) && cartItem.getQuantity() == newQuantity));
    }


    @Test
    void testAddCartItem_ExistingCartItems() throws Exception {
        int cartId = 1;
        int productId = 1;
        int newQuantity = 7;

        Cart cart = new Cart();
        Product existingProduct = new Product();
        existingProduct.setProductId(productId);

        List<CartItems> cartItemsList = new ArrayList<>();
        CartItems existingCartItem = new CartItems();
        existingCartItem.setProduct(existingProduct);
        cartItemsList.add(existingCartItem);
        unMockedCartItemsService.addCartItem(cartId, productId, newQuantity);
    }


    @Test
    void testUpdateCartItem_ExistingCartItems() throws Exception {
        int cartId = 1;
        int productId = 2;
        int newQuantity = 1;

        Cart cart = new Cart();
        Product existingProduct = new Product();
        existingProduct.setProductId(productId);

        List<CartItems> cartItemsList = new ArrayList<>();
        CartItems existingCartItem = new CartItems();
        existingCartItem.setProduct(existingProduct);
        cartItemsList.add(existingCartItem);
        unMockedCartItemsService.addCartItem(cartId, productId, newQuantity);
    }

    @Test
    void shouldRemoveProductMocked() {

        int cartItemsId = 1;

        cartItemsService.removeProduct(cartItemsId);

        verify(cartItemsRepository).removeById(cartItemsId);
    }

    @Test
    void shouldRemoveProduct() {
        int cartItemId=2;

        unMockedCartItemsService.removeProduct(cartItemId);

    }

    @Test
    void shouldGetTotalCartSize() throws Exception {
        int cartId = 1;

        int size = unMockedCartItemsService.getCartSize(cartId);

        System.out.println(size);
    }

    @Test
    void shouldFetchAllCartItems() throws Exception {
        int cartId = 1;
        List<CartItems> cartItemsList = unMockedCartItemsService.getCartItems(cartId);
        System.out.println(cartItemsList);
    }

    @Test
    void shouldFetchTotalPrice() throws Exception {
        int cartId = 1;
        double totalCartPrice = unMockedCartItemsService.getTotalCartPrice(cartId);
        System.out.println(totalCartPrice);
    }

    @Test
    void shouldClearTheCartItems() throws Exception {
        int cartId = 1;
        int sizeBefore = unMockedCartItemsService.getCartSize(cartId);
        unMockedCartItemsService.clearCartItems(cartId);
        int sizeAfter = unMockedCartItemsService.getCartSize(cartId);
        System.out.println(sizeBefore);
        System.out.println(sizeAfter);
    }
}
