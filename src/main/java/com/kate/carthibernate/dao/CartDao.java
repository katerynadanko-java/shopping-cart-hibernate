package com.kate.carthibernate.dao;

import com.kate.carthibernate.domain.Cart;

import java.util.List;
import java.util.Optional;

public interface CartDao {
    List<Cart> getAllCarts();

    Cart addCart(Cart cart);

    void updateCart(Cart cart);

    Cart getCart (Long id);

    void deleteCart (Long id);

}
