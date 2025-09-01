package com.wipro.Notification.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.Notification.dto.UserActionDTO;

@RestController
public class NotificationController {

    @PostMapping("/notify")
    public String notifyUser(@RequestBody UserActionDTO userAction) {
        String message = "User " + userAction.getUsername() + " information is " + userAction.getAction();
        System.out.println(message);
        return message;
    }
}
