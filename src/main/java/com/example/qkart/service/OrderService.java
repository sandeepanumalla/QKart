package com.example.qkart.service;

import com.example.qkart.model.CartItems;
import com.example.qkart.model.Orders;
import com.example.qkart.repository.IOrderRepository;

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
        Orders orders = Orders.builder().build();;
        for(CartItems cartItems: cartItemsList) {
            createOrderWithSingleCartItem(cartItems);
        }

        // remove the cart Item
    }

    @Override
    public void createOrderWhenCartItemsDontExist(int cartId, int productId) throws Exception {
        //crate cartItem
        cartItemsService.addCartItem(cartId, productId, 1);
        //make an order
        CartItems cartItem = cartItemsService.getCartItemByProductId(productId);
        createOrderWithSingleCartItem(cartItem);

        //remove the cartItem
        cartItemsService.removeProduct(cartItem.getId());
    }

    @Override
    public void createOrderWithSingleCartItem(CartItems cartItems) {
        //make an order
        Orders orders = Orders.builder().build();
        orderRepository.save(orders);

        //remove the cartItem
        cartItemsService.removeProduct(cartItems.getId());
    }

    @Override
    public List<Orders> getOrdersByUser() {
        return null;
    }

    @Override
    public void cancelOrderById(int orderId) {

    }
}
