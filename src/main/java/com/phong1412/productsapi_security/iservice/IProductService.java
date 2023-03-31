package com.phong1412.productsapi_security.iservice;

import com.phong1412.productsapi_security.entities.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> getAllProduct();
    List<Product> getProductsByName(String nameproduct);
    Optional<Product> getProductById(String id);
    Product createProduct(Product product);

    Product updateProduct(Product product);
    void deleteProduct(String id);

}
