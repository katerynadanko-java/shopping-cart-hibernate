package com.kate.carthibernate.service;

import com.kate.carthibernate.domain.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    Product getProduct(Long id);

    List<Product> getAll();

    void createProduct(Product product);

    String deleteById(Long id);

    Product updateProduct(Long productId, BigDecimal cost);

}
