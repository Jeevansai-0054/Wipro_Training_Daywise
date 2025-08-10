package com.wipro.ordermicroservice.service;

import com.wipro.ordermicroservice.dto.PaymentRequest;
import com.wipro.ordermicroservice.model.Order;
import com.wipro.ordermicroservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repo;

    @Autowired
    private RestTemplate restTemplate;

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
}