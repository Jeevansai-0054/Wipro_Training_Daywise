package com.wipro.ordermicroservice.controller;

import com.wipro.ordermicroservice.dto.OrderResponse;
import com.wipro.ordermicroservice.model.Order;
import com.wipro.ordermicroservice.service.OrderService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order result = service.placeOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrdersWithPayment() {
        List<OrderResponse> response = service.getAllOrdersWithPayment();
        return ResponseEntity.ok(response);
    }
}