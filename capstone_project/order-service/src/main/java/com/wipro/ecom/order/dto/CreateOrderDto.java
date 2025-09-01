package com.wipro.ecom.order.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderDto {

    @NotNull(message = "User ID is required")
    private Long userId;

    @Size(max = 500, message = "Shipping address must not exceed 500 characters")
    private String shippingAddress;

    @Size(max = 100, message = "Payment method must not exceed 100 characters")
    private String paymentMethod;

    @NotNull(message = "Cart items are required")
    private List<Long> cartItemIds;
}


