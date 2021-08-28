package com.kate.carthibernate.service;

import com.kate.carthibernate.domain.Cart;

import java.util.List;

public interface CartService {

    Cart createCart(Long customerId);

    List<Cart> getAllCarts();

    Cart addProductsToCart(Long cartId, Long productId, Integer amount);

    Cart updateProductsFromCart(Long cartId, Long productId, Integer amount);

    Cart deleteProductFromCart(Long cartId, Long productId);

    Cart deleteAllProductsFromCart(Long cartId);

}
