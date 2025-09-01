package com.wipro.ecom.order.service.impl;

import com.wipro.ecom.order.dto.*;
import com.wipro.ecom.order.entity.*;
import com.wipro.ecom.order.mapper.OrderMapper;
import com.wipro.ecom.order.repository.*;
import com.wipro.ecom.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;

    @Override
    public void addToCart(AddToCartDto addToCartDto) {
        log.info("Adding product to cart for user: {}", addToCartDto.getUserId());
        
        Cart cart = cartRepository.findByUserId(addToCartDto.getUserId())
                .orElseGet(() -> {
                    Cart newCart = Cart.builder()
                            .userId(addToCartDto.getUserId())
                            .totalAmount(BigDecimal.ZERO)
                            .build();
                    return cartRepository.save(newCart);
                });
        
        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .productId(addToCartDto.getProductId())
                .productName(addToCartDto.getProductName())
                .quantity(addToCartDto.getQuantity())
                .unitPrice(addToCartDto.getUnitPrice())
                .totalPrice(addToCartDto.getUnitPrice().multiply(BigDecimal.valueOf(addToCartDto.getQuantity())))
                .build();
        
        cartItemRepository.save(cartItem);
        
        // Update cart total
        updateCartTotal(cart);
    }

    @Override
    public void removeFromCart(Long itemId) {
        log.info("Removing cart item: {}", itemId);
        
        CartItem cartItem = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        
        Cart cart = cartItem.getCart();
        cartItemRepository.delete(cartItem);
        
        // Update cart total
        updateCartTotal(cart);
    }

    @Override
    public void updateCartItem(UpdateCartItemDto updateCartItemDto) {
        log.info("Updating cart item: {}", updateCartItemDto.getCartItemId());
        
        CartItem cartItem = cartItemRepository.findById(updateCartItemDto.getCartItemId())
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        
        cartItem.setQuantity(updateCartItemDto.getQuantity());
        cartItem.setTotalPrice(cartItem.getUnitPrice().multiply(BigDecimal.valueOf(updateCartItemDto.getQuantity())));
        
        cartItemRepository.save(cartItem);
        
        // Update cart total
        updateCartTotal(cartItem.getCart());
    }

    @Override
    @Transactional(readOnly = true)
    public CartView getUserCart(Long userId) {
        log.info("Fetching cart for user: {}", userId);
        
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user: " + userId));
        
        return orderMapper.toCartView(cart);
    }

    @Override
    public OrderView createOrder(CreateOrderDto createOrderDto) {
        log.info("Creating order for user: {}", createOrderDto.getUserId());
        
        Cart cart = cartRepository.findByUserId(createOrderDto.getUserId())
                .orElseThrow(() -> new RuntimeException("Cart not found for user: " + createOrderDto.getUserId()));
        
        // Create order
        Order order = Order.builder()
                .userId(createOrderDto.getUserId())
                .orderDate(LocalDateTime.now())
                .totalAmount(cart.getTotalAmount())
                .status(OrderStatus.PENDING)
                .shippingAddress(createOrderDto.getShippingAddress())
                .paymentMethod(createOrderDto.getPaymentMethod())
                .build();
        
        Order savedOrder = orderRepository.save(order);
        
        // Create order items from cart items
        List<CartItem> cartItems = cartItemRepository.findByCartId(cart.getId());
        List<OrderItem> orderItems = cartItems.stream()
                .map(cartItem -> OrderItem.builder()
                        .order(savedOrder)
                        .productId(cartItem.getProductId())
                        .productName(cartItem.getProductName())
                        .quantity(cartItem.getQuantity())
                        .unitPrice(cartItem.getUnitPrice())
                        .totalPrice(cartItem.getTotalPrice())
                        .build())
                .collect(Collectors.toList());
        
        orderItemRepository.saveAll(orderItems);
        
        // Clear cart
        cartItemRepository.deleteByCartId(cart.getId());
        cartRepository.delete(cart);
        
        return orderMapper.toOrderView(savedOrder);
    }

    @Override
    public OrderView updateOrderStatus(Long orderId, String status) {
        log.info("Updating order: {} with status: {}", orderId, status);
        
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
        
        order.setStatus(status);
        Order updatedOrder = orderRepository.save(order);
        
        return orderMapper.toOrderView(updatedOrder);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderView> getAllOrders() {
        log.info("Fetching all orders");
        return orderRepository.findAll().stream()
                .map(orderMapper::toOrderView)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderView> getOrdersByUser(Long userId) {
        log.info("Fetching orders for user: {}", userId);
        return orderRepository.findByUserId(userId).stream()
                .map(orderMapper::toOrderView)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public OrderView getOrderById(Long orderId) {
        log.info("Fetching order by ID: {}", orderId);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
        return orderMapper.toOrderView(order);
    }

    private void updateCartTotal(Cart cart) {
        List<CartItem> items = cartItemRepository.findByCartId(cart.getId());
        BigDecimal total = items.stream()
                .map(CartItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        cart.setTotalAmount(total);
        cartRepository.save(cart);
    }
}


