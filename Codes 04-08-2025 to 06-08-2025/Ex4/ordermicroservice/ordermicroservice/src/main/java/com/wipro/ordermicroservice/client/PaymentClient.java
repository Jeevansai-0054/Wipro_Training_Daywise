package com.wipro.ordermicroservice.client;

import com.wipro.ordermicroservice.dto.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "paymentmicroservice", fallback = PaymentFallback.class)
public interface PaymentClient {

    @GetMapping("/payments/order/{orderId}")
    PaymentResponse getPaymentByOrderId(@PathVariable int orderId);
}