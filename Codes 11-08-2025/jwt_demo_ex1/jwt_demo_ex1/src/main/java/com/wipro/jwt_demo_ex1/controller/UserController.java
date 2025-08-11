package com.wipro.jwt_demo_ex1.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class UserController {

    private final String SECRET = "mySecretKey";

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestHeader("user") String username,
                                   @RequestHeader("password") String pwd) {

        if (!"Jeevan".equals(username) || !"pass".equals(pwd)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                 .body(Map.of("error", "Invalid credentials"));
        }

        String token = Jwts.builder()
            .setSubject(username)
            .claim("authorities", List.of("ROLE_USER"))
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 86400000))
            .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
            .compact();

        return ResponseEntity.ok(Map.of(
            "user", username,
            "token", "Bearer " + token
        ));
    }
}