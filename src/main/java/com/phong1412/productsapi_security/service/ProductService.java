package com.phong1412.productsapi_security.service;

import com.phong1412.productsapi_security.entities.Product;
import com.phong1412.productsapi_security.exception.BadException;
import com.phong1412.productsapi_security.exception.NotFoundException;
import com.phong1412.productsapi_security.iservice.IProductService;
import com.phong1412.productsapi_security.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    @Override
    public List<Product> getAllProduct() {
       return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByName(String nameproduct) {
        List<Product> listProdcutAfterFind = productRepository.findAll().stream().filter(product -> product.getName().contains(nameproduct)).toList();
        if(listProdcutAfterFind.stream().count() > 0) {
            return listProdcutAfterFind;
        }
       throw new NotFoundException("Không tìm thấy sản phẩm nào có tên: "+nameproduct+" trong database!");
    }

    @Override
    public Optional<Product> getProductById(String id) {
        if(productRepository.findProductById(id).isPresent()) {
            return productRepository.findProductById(id);
        }
       throw new NotFoundException("Không tìm thấy product có id: "+ id);
    }

    @Override
    public Product createProduct(Product product) {
        if(!productRepository.findProductByName(product.getName()).isPresent()) {
            product.setId(UUID.randomUUID().toString());
            return productRepository.save(product);
        }
       throw new BadException("Sản phẩm đã tồn tại!");
    }

    @Override
    public Product updateProduct(Product product) {
        if(productRepository.findProductById(product.getId()).isPresent()) {
            return productRepository.save(product);
        }
        throw new BadException("Không tìm thấy sản phẩm có id: "+product.getId()+" để cập nhật!");
    }

    @Override
    public void deleteProduct(String id) {
        if(productRepository.findProductById(id).isPresent()) {
          Product product =  productRepository.findProductById(id).get();
          productRepository.delete(product);
        }
        else {
          throw new BadException("Không tìm thấy product có id: "+id +" để xóa!!!");
        }
    }
}
