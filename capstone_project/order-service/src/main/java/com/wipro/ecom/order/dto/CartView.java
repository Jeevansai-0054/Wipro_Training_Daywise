package com.wipro.ecom.order.dto;

import com.wipro.ecom.order.entity.CartItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartView {

    private Long id;
    private Long userId;
    private List<CartItem> items;
    private BigDecimal totalAmount;
    private Integer itemCount;
}
