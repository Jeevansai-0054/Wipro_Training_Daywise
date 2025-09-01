package com.wipro.jwt_demo_ex1.controller;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeController {

    @GetMapping("/getCurrentTime")
    public ResponseEntity<?> getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        return ResponseEntity.ok(Map.of("currentTime", now.toString()));
    }
}