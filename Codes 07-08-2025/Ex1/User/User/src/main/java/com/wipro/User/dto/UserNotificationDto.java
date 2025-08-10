package com.wipro.User.dto;

public class UserNotificationDto {

    private String username;
    private String password;
    private String address;
    private String action;

    // Constructors
    public UserNotificationDto() {
    }

    public UserNotificationDto(String username, String password, String address, String action) {
        this.username = username;
        this.password = password;
        this.address = address;
        this.action = action;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
