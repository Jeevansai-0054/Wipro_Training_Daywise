package com.wipro.ecom.orderservice.service;

import com.wipro.ecom.orderservice.entity.Order;
import com.wipro.ecom.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Order placeOrder(Order order) {
        order.setOrderStatus("PENDING");
        order.setOrderDate(LocalDateTime.now());
        return repository.save(order);
    }

    public List<Order> getOrdersByUser(Long userId) {
        return repository.findByUserId(userId);
    }

    public Optional<Order> getOrderByIdAndUserId(Long orderId, Long userId) {
        Optional<Order> orderOpt = repository.findById(orderId);
        if (orderOpt.isPresent() && orderOpt.get().getUserId().equals(userId)) {
            return orderOpt;
        }
        return Optional.empty();
    }

    public boolean cancelOrder(Long orderId, Long userId) {
        Optional<Order> orderOpt = getOrderByIdAndUserId(orderId, userId);
        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            order.setOrderStatus("CANCELLED");
            repository.save(order);
            return true;
        }
        return false;
    }
}
