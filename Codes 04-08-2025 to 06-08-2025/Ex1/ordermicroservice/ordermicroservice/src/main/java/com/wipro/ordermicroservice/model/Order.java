package com.wipro.ordermicroservice.model;

import jakarta.persistence.*;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String orderNumber;
    private double orderValue;
    private String orderStatus;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public double getOrderValue() {
		return orderValue;
	}
	public void setOrderValue(double orderValue) {
		this.orderValue = orderValue;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", orderNumber=" + orderNumber + ", orderValue=" + orderValue + ", orderStatus="
				+ orderStatus + "]";
	}
	public Order(int id, String orderNumber, double orderValue, String orderStatus) {
		super();
		this.id = id;
		this.orderNumber = orderNumber;
		this.orderValue = orderValue;
		this.orderStatus = orderStatus;
	}

   
}