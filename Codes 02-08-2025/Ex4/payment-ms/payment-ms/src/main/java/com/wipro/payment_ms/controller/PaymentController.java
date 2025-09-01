package com.wipro.payment_ms.controller;

import com.wipro.payment_ms.entity.Payment;
import com.wipro.payment_ms.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentRepository repo;

    @PostMapping
    public Payment create(@RequestBody Payment payment) {
        return repo.save(payment);
    }

    @GetMapping("/{id}")
    public Payment getById(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }

    @GetMapping
    public List<Payment> getAll() {
        return repo.findAll();
    }

    @PutMapping("/{id}")
    public Payment update(@PathVariable Long id, @RequestBody Payment payment) {
        payment.setId(id);
        return repo.save(payment);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        repo.deleteById(id);
        return "Deleted successfully";
    }
}