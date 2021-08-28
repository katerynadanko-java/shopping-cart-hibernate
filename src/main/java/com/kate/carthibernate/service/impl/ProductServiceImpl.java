package com.kate.carthibernate.service.impl;

import com.kate.carthibernate.dao.ProductDao;
import com.kate.carthibernate.domain.Product;
import com.kate.carthibernate.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProduct(Long id) {

        return productDao.getProduct(id);
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAllProducts();
    }

    @Override
    public void createProduct(Product product) {
        productDao.addProduct(product);
    }

    @Override
    public String deleteById(Long id) {
        productDao.deleteProduct(id);
        return "Product " + id + " deleted";
    }

    @Override
    public Product updateProduct(Long productId, BigDecimal price) {
        Product product = productDao.getProduct(productId);
        if (price.compareTo(new BigDecimal(0)) < 0) {
            throw new RuntimeException("Product should not cost less then 0");
        }
        product.setPrice(price);
        return product;
    }

}
