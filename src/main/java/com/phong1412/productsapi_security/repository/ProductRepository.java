package com.phong1412.productsapi_security.repository;

import com.phong1412.productsapi_security.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductById(String id);
    Optional<Product> findProductByName(String name);

}
