package com.wipro.ecom.product.service;

import com.wipro.ecom.product.dto.ProductCreateDto;
import com.wipro.ecom.product.dto.ProductUpdateDto;
import com.wipro.ecom.product.dto.ProductView;

import java.util.List;

public interface ProductService {
    List<ProductView> getAllProducts();
    ProductView getProductById(Long id);
    ProductView createProduct(ProductCreateDto productCreateDto);
    ProductView updateProduct(ProductUpdateDto productUpdateDto);
    void deleteProduct(Long id);
    boolean reserveStock(Long productId, Integer quantity);
    void releaseStock(Long productId, Integer quantity);
}
