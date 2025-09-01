package com.wipro.employeemanagemnt.model;

import jakarta.persistence.*;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Add more fields if needed later

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
}