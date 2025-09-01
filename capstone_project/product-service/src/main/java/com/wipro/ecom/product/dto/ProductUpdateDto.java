package com.wipro.ecom.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateDto {

    @NotNull(message = "Product ID is required")
    private Long id;

    @Size(max = 255, message = "Product name must not exceed 255 characters")
    private String name;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    private String description;

    @Positive(message = "Price must be positive")
    private BigDecimal price;

    @Positive(message = "Quantity must be positive")
    private Integer quantity;

    @Size(max = 100, message = "SKU must not exceed 100 characters")
    private String sku;

    @Size(max = 100, message = "Category must not exceed 100 characters")
    private String category;

    @Size(max = 500, message = "Image URL must not exceed 500 characters")
    private String imageUrl;

    private Boolean active;
}
