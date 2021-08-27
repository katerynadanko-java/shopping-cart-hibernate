package com.kate.carthibernate.dao;

import com.kate.carthibernate.domain.Product;

import java.util.List;

public interface ProductDao {
     List<Product> getAllProducts();

     Product getProduct(Long id);

     void addProduct(Product product);

     void updateProduct(Product product);

     void deleteProduct(Long id);
}
