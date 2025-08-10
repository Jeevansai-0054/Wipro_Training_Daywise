package com.wipro.ordermicroservice.service;

import com.wipro.ordermicroservice.client.PaymentClient;
import com.wipro.ordermicroservice.dto.OrderResponse;
import com.wipro.ordermicroservice.dto.PaymentRequest;
import com.wipro.ordermicroservice.dto.PaymentResponse;
import com.wipro.ordermicroservice.model.Order;
import com.wipro.ordermicroservice.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repo;

    @Autowired
    private PaymentClient paymentClient;

    public Order placeOrder(Order order) {
        order.setOrderStatus("I");
        Order savedOrder = repo.save(order);

        PaymentRequest payment = new PaymentRequest();
        payment.setOrderId(savedOrder.getId());
        payment.setPaymentStatus(true);

        try {
            // Simulate payment processing via Feign (optional POST method)
            savedOrder.setOrderStatus("P");
        } catch (Exception e) {
            savedOrder.setOrderStatus("C");
        }

        return repo.save(savedOrder);
    }

    @CircuitBreaker(name = "order-cb", fallbackMethod = "fallbackPayment")
    public List<OrderResponse> getAllOrdersWithPayment() {
        List<Order> orders = repo.findAll();
        List<OrderResponse> responseList = new ArrayList<>();

        for (Order order : orders) {
            OrderResponse response = new OrderResponse();
            response.setOrderId(order.getId());
            response.setOrderNumber(order.getOrderNumber());
            response.setOrderValue(order.getOrderValue());
            response.setOrderStatus(order.getOrderStatus());

            PaymentResponse payment = paymentClient.getPaymentByOrderId(order.getId());
            response.setPaymentStatus(payment.isPaymentStatus());

            responseList.add(response);
        }

        return responseList;
    }

    public List<OrderResponse> fallbackPayment(Throwable t) {
        List<Order> orders = repo.findAll();
        List<OrderResponse> responseList = new ArrayList<>();

        for (Order order : orders) {
            OrderResponse response = new OrderResponse();
            response.setOrderId(order.getId());
            response.setOrderNumber(order.getOrderNumber());
            response.setOrderValue(order.getOrderValue());
            response.setOrderStatus(order.getOrderStatus());
            response.setPaymentStatus(false); // fallback

            responseList.add(response);
        }

        return responseList;
    }
}