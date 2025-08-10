package com.wipro.paymentmicroservice.model;

import jakarta.persistence.*;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int orderId;
    private boolean paymentStatus;

    // Getters and Setters
}