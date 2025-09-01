package com.wipro.productmanagemnt.repository;

import com.wipro.productmanagemnt.model.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByProductMake(String productMake);
}