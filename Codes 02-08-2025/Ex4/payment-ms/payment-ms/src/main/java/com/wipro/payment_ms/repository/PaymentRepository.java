package com.wipro.payment_ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wipro.payment_ms.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {}