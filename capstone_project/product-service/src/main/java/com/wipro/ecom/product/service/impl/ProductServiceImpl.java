package com.wipro.ecom.product.service.impl;

import com.wipro.ecom.product.dto.ProductCreateDto;
import com.wipro.ecom.product.dto.ProductUpdateDto;
import com.wipro.ecom.product.dto.ProductView;
import com.wipro.ecom.product.entity.Product;
import com.wipro.ecom.product.mapper.ProductMapper;
import com.wipro.ecom.product.repository.ProductRepository;
import com.wipro.ecom.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ProductView> getAllProducts() {
        log.info("Fetching all products");
        return productRepository.findAll().stream()
                .map(productMapper::toProductView)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductView getProductById(Long id) {
        log.info("Fetching product by ID: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return productMapper.toProductView(product);
    }

    @Override
    public ProductView createProduct(ProductCreateDto productCreateDto) {
        log.info("Creating new product: {}", productCreateDto.getName());
        
        if (productRepository.existsBySku(productCreateDto.getSku())) {
            throw new RuntimeException("Product with SKU " + productCreateDto.getSku() + " already exists");
        }
        
        Product product = productMapper.toProduct(productCreateDto);
        Product savedProduct = productRepository.save(product);
        return productMapper.toProductView(savedProduct);
    }

    @Override
    public ProductView updateProduct(ProductUpdateDto productUpdateDto) {
        log.info("Updating product: {}", productUpdateDto.getId());
        
        Product product = productRepository.findById(productUpdateDto.getId())
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productUpdateDto.getId()));
        
        productMapper.updateProductFromDto(productUpdateDto, product);
        Product updatedProduct = productRepository.save(product);
        return productMapper.toProductView(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        log.info("Deleting product with ID: {}", id);
        
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        
        productRepository.deleteById(id);
    }

    @Override
    @Transactional
    public boolean reserveStock(Long productId, Integer quantity) {
        log.info("Reserving stock for product: {}, quantity: {}", productId, quantity);
        
        int updatedRows = productRepository.reserveStock(productId, quantity);
        return updatedRows > 0;
    }

    @Override
    @Transactional
    public void releaseStock(Long productId, Integer quantity) {
        log.info("Releasing stock for product: {}, quantity: {}", productId, quantity);
        
        int updatedRows = productRepository.releaseStock(productId, quantity);
        if (updatedRows == 0) {
            throw new RuntimeException("Failed to release stock for product: " + productId);
        }
    }
}


