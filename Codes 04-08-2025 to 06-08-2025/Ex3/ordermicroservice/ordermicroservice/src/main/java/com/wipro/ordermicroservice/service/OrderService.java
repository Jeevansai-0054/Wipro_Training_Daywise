package com.wipro.ordermicroservice.service;

import com.wipro.ordermicroservice.dto.OrderResponse;
import com.wipro.ordermicroservice.dto.PaymentRequest;
import com.wipro.ordermicroservice.dto.PaymentResponse;
import com.wipro.ordermicroservice.model.Order;
import com.wipro.ordermicroservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repo;

    @Autowired
    private RestTemplate restTemplate;

    // 🔹 Place Order and call payment-ms
    public Order placeOrder(Order order) {
        order.setOrderStatus("I");
        Order savedOrder = repo.save(order);

        PaymentRequest payment = new PaymentRequest();
        payment.setOrderId(savedOrder.getId());
        payment.setPaymentStatus(true);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(
                "http://localhost:9011/payments", payment, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                savedOrder.setOrderStatus("P");
            } else {
                savedOrder.setOrderStatus("C");
            }
        } catch (Exception e) {
            savedOrder.setOrderStatus("C");
        }

        return repo.save(savedOrder);
    }

    
    public List<OrderResponse> getAllOrdersWithPayment() {
        List<Order> orders = repo.findAll();
        List<OrderResponse> responseList = new ArrayList<>();

        for (Order order : orders) {
            OrderResponse response = new OrderResponse();
            response.setOrderId(order.getId()); 
            response.setOrderNumber(order.getOrderNumber()); 
            response.setOrderValue(order.getOrderValue());
            response.setOrderStatus(order.getOrderStatus());

            try {
                PaymentResponse payment = restTemplate.getForObject(
                    "http://localhost:9011/payments/order/" + order.getId(),
                    PaymentResponse.class);

                response.setPaymentStatus(payment != null && payment.isPaymentStatus());
            } catch (Exception e) {
                response.setPaymentStatus(false); // fallback if payment not found
            }

            responseList.add(response);
        }

        return responseList;
    }
}