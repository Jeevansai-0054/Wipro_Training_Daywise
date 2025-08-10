package com.wipro.ordermicroservice.dto;

public class OrderResponse {

    private int orderId;
    private String orderNumber;
    private Double orderValue;
    private String orderStatus;
    private boolean paymentStatus;


    public int getOrderId() {
        return orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public Double getOrderValue() {
        return orderValue;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

   
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setOrderValue(Double orderValue) {
        this.orderValue = orderValue;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}