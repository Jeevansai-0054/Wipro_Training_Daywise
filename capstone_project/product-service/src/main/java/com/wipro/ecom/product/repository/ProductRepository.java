package com.wipro.ecom.product.repository;

import com.wipro.ecom.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    Optional<Product> findBySku(String sku);
    
    boolean existsBySku(String sku);
    
    @Query("SELECT p.quantity FROM Product p WHERE p.id = :productId")
    Optional<Integer> findQuantityById(@Param("productId") Long productId);
    
    @Query("UPDATE Product p SET p.quantity = p.quantity - :quantity WHERE p.id = :productId AND p.quantity >= :quantity")
    int reserveStock(@Param("productId") Long productId, @Param("quantity") Integer quantity);
    
    @Query("UPDATE Product p SET p.quantity = p.quantity + :quantity WHERE p.id = :productId")
    int releaseStock(@Param("productId") Long productId, @Param("quantity") Integer quantity);
}


