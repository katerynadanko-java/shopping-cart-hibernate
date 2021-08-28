package com.kate.carthibernate.controller;

import com.kate.carthibernate.domain.Product;
import com.kate.carthibernate.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/findById/{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") Long id) {
        log.debug("Start to find product with id ", id);
        Product product = productService.getProduct(id);
        log.debug("Found product with id ", product.getId());
        return ResponseEntity.ok(product);
    }

    @Transactional
    @PostMapping(value = "/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        log.debug("Start to create product ", product);
        productService.createProduct(product);
        return ResponseEntity.ok(product);
    }

    @Transactional
    @PutMapping("update/{productId}/{cost}")
    public ResponseEntity<Product> update(@PathVariable Long productId, @PathVariable BigDecimal cost) throws IOException {
        log.debug("Start to update product with id ", productId);
        return ResponseEntity.ok(productService.updateProduct(productId, cost));
    }

    @GetMapping("get")
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    @Transactional
    @DeleteMapping("delete/{productId}")
    public ResponseEntity<String> delete(@PathVariable Long productId) {
        log.debug("Start to delete product with id ", productId);
        return ResponseEntity.ok(productService.deleteById(productId));
    }


}
