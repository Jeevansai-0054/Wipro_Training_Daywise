package com.wipro.ecom.product.controller;

import com.wipro.ecom.product.dto.ProductCreateDto;
import com.wipro.ecom.product.dto.ProductUpdateDto;
import com.wipro.ecom.product.dto.ProductView;
import com.wipro.ecom.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductView>> getAllProducts() {
        log.info("Fetching all products");
        List<ProductView> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductView> getProductById(@PathVariable Long id) {
        log.info("Fetching product by ID: {}", id);
        ProductView product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductView> createProduct(@Valid @RequestBody ProductCreateDto productCreateDto) {
        log.info("Creating new product: {}", productCreateDto.getName());
        ProductView product = productService.createProduct(productCreateDto);
        return ResponseEntity.ok(product);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductView> updateProduct(@Valid @RequestBody ProductUpdateDto productUpdateDto) {
        log.info("Updating product: {}", productUpdateDto.getId());
        ProductView product = productService.updateProduct(productUpdateDto);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        log.info("Deleting product: {}", id);
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/internal/reserve")
    public ResponseEntity<Boolean> reserveStock(@RequestParam Long productId, @RequestParam Integer quantity) {
        log.info("Reserving stock for product: {}, quantity: {}", productId, quantity);
        boolean reserved = productService.reserveStock(productId, quantity);
        return ResponseEntity.ok(reserved);
    }

    @PostMapping("/internal/release")
    public ResponseEntity<Void> releaseStock(@RequestParam Long productId, @RequestParam Integer quantity) {
        log.info("Releasing stock for product: {}, quantity: {}", productId, quantity);
        productService.releaseStock(productId, quantity);
        return ResponseEntity.ok().build();
    }
}
