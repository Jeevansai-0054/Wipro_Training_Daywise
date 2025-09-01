package com.wipro.paymentmicroservice.service;

import com.wipro.paymentmicroservice.model.Payment;
import com.wipro.paymentmicroservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repo;

    public Payment processPayment(Payment payment) {
        return repo.save(payment);
    }
}
