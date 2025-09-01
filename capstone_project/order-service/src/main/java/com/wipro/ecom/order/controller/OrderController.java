package com.wipro.ecom.order.controller;

import com.wipro.ecom.order.dto.*;
import com.wipro.ecom.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    // Cart endpoints
    @PostMapping("/cart/addProd")
    public ResponseEntity<Void> addToCart(@Valid @RequestBody AddToCartDto addToCartDto) {
        log.info("Adding product to cart for user: {}, product: {}", addToCartDto.getUserId(), addToCartDto.getProductId());
        orderService.addToCart(addToCartDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/cart/deleteProd/{itemId}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Long itemId) {
        log.info("Removing item from cart: {}", itemId);
        orderService.removeFromCart(itemId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/cart/update")
    public ResponseEntity<Void> updateCartItem(@Valid @RequestBody UpdateCartItemDto updateCartItemDto) {
        log.info("Updating cart item: {}", updateCartItemDto.getCartItemId());
        orderService.updateCartItem(updateCartItemDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/cart/{userId}")
    public ResponseEntity<CartView> getUserCart(@PathVariable Long userId) {
        log.info("Fetching cart for user: {}", userId);
        CartView cart = orderService.getUserCart(userId);
        return ResponseEntity.ok(cart);
    }

    // Order endpoints
    @PostMapping
    public ResponseEntity<OrderView> createOrder(@Valid @RequestBody CreateOrderDto createOrderDto) {
        log.info("Creating order for user: {}", createOrderDto.getUserId());
        OrderView order = orderService.createOrder(createOrderDto);
        return ResponseEntity.ok(order);
    }

    @PutMapping("/{orderId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OrderView> updateOrder(@PathVariable Long orderId, @RequestParam String status) {
        log.info("Updating order: {} with status: {}", orderId, status);
        OrderView order = orderService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok(order);
    }

    @GetMapping
    public ResponseEntity<List<OrderView>> getOrders(@RequestParam(required = false) Long userId) {
        if (userId != null) {
            log.info("Fetching orders for user: {}", userId);
            List<OrderView> orders = orderService.getOrdersByUser(userId);
            return ResponseEntity.ok(orders);
        } else {
            log.info("Fetching all orders");
            List<OrderView> orders = orderService.getAllOrders();
            return ResponseEntity.ok(orders);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<OrderView>> getOrdersByUser(@PathVariable Long userId) {
        log.info("Fetching orders for user: {}", userId);
        List<OrderView> orders = orderService.getOrdersByUser(userId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/details/{orderId}")
    public ResponseEntity<OrderView> getOrderDetails(@PathVariable Long orderId) {
        log.info("Fetching order details: {}", orderId);
        OrderView order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }
}
