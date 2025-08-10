package com.wipro.productmanagemnt.service;

import com.wipro.productmanagemnt.model.Product;
import com.wipro.productmanagemnt.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;



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
    public List<Product> getProductsByNameAndMakeSortedDesc(String productName, String productMake) {
        return repo.findByProductNameAndProductMakeOrderByProductNameDesc(productName, productMake);
    }
    public Page<Product> getProductsPaginatedSortedByPrice(int page, int size) {
    PageRequest pageable = PageRequest.of(page, size, Sort.by("productPrice").ascending());
    return repo.findAll(pageable);
}


}
