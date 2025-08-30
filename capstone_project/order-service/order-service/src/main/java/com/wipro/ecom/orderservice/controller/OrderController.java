package com.wipro.ecom.orderservice.controller;

import com.wipro.ecom.orderservice.entity.Order;
import com.wipro.ecom.orderservice.service.OrderService;
import com.wipro.ecom.orderservice.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final JwtUtil jwtUtil;

    public OrderController(OrderService orderService, JwtUtil jwtUtil) {
        this.orderService = orderService;
        this.jwtUtil = jwtUtil;
    }

    private Long extractUserIdFromRequest(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        return jwtUtil.extractUserId(token);
    }

    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody Order order,
                                            HttpServletRequest request) {
        Long userId = extractUserIdFromRequest(request);
        order.setUserId(userId);
        Order placedOrder = orderService.placeOrder(order);
        return ResponseEntity.ok(placedOrder);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getUserOrders(HttpServletRequest request) {
        Long userId = extractUserIdFromRequest(request);
        List<Order> orders = orderService.getOrdersByUser(userId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId,
                                              HttpServletRequest request) {
        Long userId = extractUserIdFromRequest(request);
        return orderService.getOrderByIdAndUserId(orderId, userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> cancelOrder(@PathVariable Long orderId,
                                              HttpServletRequest request) {
        Long userId = extractUserIdFromRequest(request);
        boolean cancelled = orderService.cancelOrder(orderId, userId);
        if (cancelled) {
            return ResponseEntity.ok("Order cancelled successfully");
        }
        return ResponseEntity.status(403).body("Unauthorized or order not found");
    }
}
