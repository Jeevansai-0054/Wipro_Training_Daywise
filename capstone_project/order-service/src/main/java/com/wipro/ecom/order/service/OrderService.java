package com.wipro.ecom.order.service;

import com.wipro.ecom.order.dto.*;

import java.util.List;

public interface OrderService {
    // Cart methods
    void addToCart(AddToCartDto addToCartDto);
    void removeFromCart(Long itemId);
    void updateCartItem(UpdateCartItemDto updateCartItemDto);
    CartView getUserCart(Long userId);
    
    // Order methods
    OrderView createOrder(CreateOrderDto createOrderDto);
    OrderView updateOrderStatus(Long orderId, String status);
    List<OrderView> getAllOrders();
    List<OrderView> getOrdersByUser(Long userId);
    OrderView getOrderById(Long orderId);
}
