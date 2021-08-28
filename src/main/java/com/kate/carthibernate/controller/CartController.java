package com.kate.carthibernate.controller;

import com.kate.carthibernate.domain.Cart;
import com.kate.carthibernate.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @Transactional
    @PostMapping("create/{customerId}")
    public ResponseEntity<Cart> createCart(@PathVariable Long customerId) {
        log.debug("Star to add cart with customerId ", customerId);
        return ResponseEntity.ok(cartService.createCart(customerId));
    }

    @GetMapping("get")
    public ResponseEntity<List<Cart>> getAllCarts() {
        log.debug("Start to find carts");
        return ResponseEntity.ok(cartService.getAllCarts());
    }

    @PutMapping("/{cartId}/add/product/{productId}/{amount}")
    public ResponseEntity<Cart> addProductToCart(@PathVariable Long cartId, @PathVariable Long productId, @PathVariable Integer amount) {
        log.debug("Start to add product with id ", productId, "to customer with id", cartId);
        return ResponseEntity.ok(cartService.addProductsToCart(cartId, productId, amount));
    }

    @Transactional
    @PutMapping("/{cartId}/update/product/{productId}/{amount}")
    public ResponseEntity<Cart> updateProductAmountInCart(@PathVariable Long cartId, @PathVariable Long productId, @PathVariable Integer amount) {
        log.debug("Start to delete product with id ", productId);
        return ResponseEntity.ok(cartService.updateProductsFromCart(cartId, productId, amount));
    }

    @Transactional
    @DeleteMapping("/{cartId}/delete/product/{productId}")
    public ResponseEntity<Cart> deleteProductFromCart(@PathVariable Long cartId, @PathVariable Long productId) {
        log.debug("Start to delete product with id ", productId);
        return ResponseEntity.ok(cartService.deleteProductFromCart(cartId, productId));
    }

    @Transactional
    @DeleteMapping("/{cartId}/delete/products")
    public ResponseEntity<Cart> deleteProductFromCart(@PathVariable Long cartId) {
        log.debug("Start to delete product with id ");
        return ResponseEntity.ok(cartService.deleteAllProductsFromCart(cartId));
    }
}
