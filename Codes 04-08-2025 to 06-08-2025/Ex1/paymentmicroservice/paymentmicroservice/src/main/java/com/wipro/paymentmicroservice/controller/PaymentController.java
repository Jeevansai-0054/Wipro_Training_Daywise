package com.wipro.paymentmicroservice.controller;

import com.wipro.paymentmicroservice.model.Payment;
import com.wipro.paymentmicroservice.service.PaymentService;
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
}