package com.wipro.paymentms.controller;

import com.wipro.paymentms.model.Payment;
import com.wipro.paymentms.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService service;

   
    @PostMapping
    public ResponseEntity<Payment> addPayment(@RequestBody Payment payment) {
        Payment saved = service.savePayment(payment);

        HttpHeaders headers = new HttpHeaders();
        headers.add("created-date", LocalDate.now().toString());

        return new ResponseEntity<>(saved, headers, HttpStatus.CREATED);
    }


    @PutMapping
    public ResponseEntity<String> updatePayment(@RequestBody Payment payment) {
        service.updatePayment(payment);
        return ResponseEntity.ok("Data modified successfully");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePayment(@PathVariable int id) {
        service.deletePayment(id);
        return ResponseEntity.ok("Data deleted successfully");
    }


    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPayment(@PathVariable int id) {
        Payment payment = service.getPaymentById(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("search-time", LocalDateTime.now().toString());

        if (payment == null) {
            return new ResponseEntity<>(null, headers, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(payment, headers, HttpStatus.OK);
    }

   
    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = service.getAllPayments();

        HttpHeaders headers = new HttpHeaders();
        headers.add("search-time", LocalDateTime.now().toString());

        if (payments.isEmpty()) {
            return new ResponseEntity<>(null, headers, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(payments, headers, HttpStatus.OK);
    }
}