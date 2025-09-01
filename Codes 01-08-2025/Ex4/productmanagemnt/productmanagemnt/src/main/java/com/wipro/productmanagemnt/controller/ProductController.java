package com.wipro.productmanagemnt.controller;

import com.wipro.productmanagemnt.model.Product;
import com.wipro.productmanagemnt.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;



import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return service.saveProduct(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable int id) {
        return service.getProductById(id);
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        return service.updateProduct(product);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        service.deleteProduct(id);
        return "Product removed with id: " + id;
    }
    @GetMapping("/search")
    public List<Product> getProductsByNameAndMakeSortedDesc(
            @RequestParam String productName,
            @RequestParam String productMake) {
        return service.getProductsByNameAndMakeSortedDesc(productName, productMake);
    }
    @GetMapping("/paginated")
    public Page<Product> getPaginatedProductsSortedByPrice(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return service.getProductsPaginatedSortedByPrice(page, size);
    }


}