package com.wipro.ordermicroservice.repository;

import com.wipro.ordermicroservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}