package com.wipro.ordermicroservice.client;

import com.wipro.ordermicroservice.dto.PaymentResponse;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallback implements PaymentClient {

    @Override
    public PaymentResponse getPaymentByOrderId(int orderId) {
        PaymentResponse fallback = new PaymentResponse();
        fallback.setOrderId(orderId);
        fallback.setPaymentStatus(false); // default fallback
        return fallback;
    }
}