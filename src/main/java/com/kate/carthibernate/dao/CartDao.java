package com.kate.carthibernate.dao;

import com.kate.carthibernate.domain.Cart;

import java.util.List;

public interface CartDao {
    List<Cart> getAllCarts();

    Cart addCart(Cart cart);

    void updateCart(Cart cart);

    Cart getCart(Long id);

}
