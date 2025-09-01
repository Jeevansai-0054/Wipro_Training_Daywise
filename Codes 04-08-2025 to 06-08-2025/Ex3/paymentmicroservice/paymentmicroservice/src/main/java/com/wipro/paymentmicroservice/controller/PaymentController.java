package com.wipro.paymentmicroservice.controller;

import com.wipro.paymentmicroservice.model.Payment;
import com.wipro.paymentmicroservice.service.PaymentService;
import com.wipro.paymentmicroservice.dto.PaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @PostMapping
    public ResponseEntity<String> makePayment(@RequestBody Payment payment) {
        service.processPayment(payment);
        return ResponseEntity.ok("Payment processed");
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<PaymentResponse> getPaymentByOrderId(@PathVariable int orderId) {
        Payment payment = service.getPaymentByOrderId(orderId);  // ✅ Use service, not repo

        if (payment != null) {
            PaymentResponse response = new PaymentResponse();
            response.setOrderId(payment.getOrderId());
            response.setPaymentStatus(payment.isPaymentStatus());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}