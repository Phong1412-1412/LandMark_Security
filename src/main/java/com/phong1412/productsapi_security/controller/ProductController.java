package com.phong1412.productsapi_security.controller;

import com.phong1412.productsapi_security.entities.Product;
import com.phong1412.productsapi_security.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProduct() {
        return ResponseEntity.ok().body(productService.getAllProduct());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable String id) {
        return ResponseEntity.ok().body(productService.getProductById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> getProductsByName(@RequestParam String productname) {
        return ResponseEntity.ok().body(productService.getProductsByName(productname));
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addUser(@RequestBody Product product ) {
        return ResponseEntity.ok().body(productService.createProduct(product));
    }

    @PutMapping("/update")
    public ResponseEntity<Product> updateUser(@RequestBody Product product) {
        return ResponseEntity.ok().body(productService.updateProduct(product));
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<?> deleteUser(@PathVariable String name ) {
        productService.deleteProduct(name);
        return ResponseEntity.notFound().build();
    }
}
