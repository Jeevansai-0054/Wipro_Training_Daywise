package com.wipro.paymentmicroservice.repository;

import com.wipro.paymentmicroservice.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	Payment findByOrderId(int orderId);
}