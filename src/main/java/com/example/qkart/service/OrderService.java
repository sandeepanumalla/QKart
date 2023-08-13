package com.example.qkart.service;

import com.example.qkart.model.CartItems;
import com.example.qkart.model.Orders;
import com.example.qkart.repository.IOrderRepository;

import java.util.Date;
import java.util.List;

public class OrderService implements IOrderService{

    private IOrderRepository orderRepository;

    private ICartItemsService cartItemsService;

    public OrderService(IOrderRepository orderRepository, ICartItemsService cartItemsService) {
        this.orderRepository = orderRepository;
        this.cartItemsService = cartItemsService;
    }


    @Override
    public void createOrderWithCartCheckout(List<CartItems> cartItemsList) {

        // make an order
        for(CartItems cartItems: cartItemsList) {
            createOrderWithSingleCartItem(cartItems);
        }

        // remove the cart Item
    }

    @Override
    public void createOrderWhenCartItemsDontExist(int cartId, int productId, int quantity) throws Exception {
        //crate cartItem
        cartItemsService.addCartItem(cartId, productId, quantity);

        //make an order
        CartItems cartItem = cartItemsService.getCartItemByProductId(productId);
        createOrderWithSingleCartItem(cartItem);

        //remove the cartItem
    }

    @Override
    public void createOrderWithSingleCartItem(CartItems cartItems) {
        //make an order
        Orders orders = Orders.builder()
                .name(cartItems.getProduct().getName())
                .quantity(cartItems.getQuantity())
                .price(cartItems.getQuantity() * cartItems.getProduct().getPrice())
                .category(cartItems.getProduct().getCategory())
                .user(cartItems.getCart().getUser())
                .createdDate(new Date())
                .build();

        orderRepository.save(orders);

        //remove the cartItem
        cartItemsService.removeProduct(cartItems.getCart().getId(), cartItems.getProduct().getProductId());
    }

    @Override
    public List<Orders> getOrdersByUser(int userId) {
       return orderRepository.getAllOrders(userId);
    }

    @Override
    public void cancelOrderById(int orderId) {
        orderRepository.removeOrder(orderId);
    }
}
