package com.wipro.productmanagemnt.service;

import com.wipro.productmanagemnt.model.Product;
import com.wipro.productmanagemnt.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public Product saveProduct(Product product) {
        return repo.save(product);
    }

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Product getProductById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Product updateProduct(Product product) {
        return repo.save(product);
    }

    public void deleteProduct(int id) {
        repo.deleteById(id);
    }
}
