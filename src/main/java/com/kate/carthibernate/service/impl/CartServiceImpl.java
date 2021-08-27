package com.kate.carthibernate.service.impl;

import com.kate.carthibernate.domain.Cart;
import com.kate.carthibernate.domain.Customer;
import com.kate.carthibernate.domain.Product;

import com.kate.carthibernate.dao.CartDao;
import com.kate.carthibernate.dao.CustomerDao;
import com.kate.carthibernate.dao.ProductDao;
import com.kate.carthibernate.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private ProductDao productDao;

    List<Product> products = new ArrayList<>();
    List<Cart> carts = new ArrayList<>();

    @Override
    public Cart createCart(Long customerId){

        Cart cart = new Cart();
        Customer customer = customerDao.getCustomer(customerId);
        Cart savedCart = cartDao.addCart(cart);
        carts.add(cart);

        customer.setCarts(carts);
        customerDao.addCustomer(customer);
        return savedCart;
    }

    @Override
    public List<Cart> getAllCarts() {
        return cartDao.getAllCarts();
    }

    @Override
    public Cart addProductsToCart(Long cartId, Long productId, Integer amount) {

        Product product = productDao.getProduct(productId);
        Cart cart = cartDao.getCart(cartId);
        product.setAmount(amount);
        products.add(product);
        cart.setProducts(products);
        BigDecimal bigDecimalSum = countSum(cart);
        cart.setSum(bigDecimalSum);
        return cartDao.addCart(cart);
    }

    @Override
    public Cart updateProductsFromCart(Long cartId, Long productId, Integer amount) {

        if (productId == null) {
            throw new RuntimeException("Required parameters: productId");
        }
        Cart cart = cartDao.getCart(cartId);
        for (Product p : cart.getProducts()) {
            if (productId.equals(p.getId())) {
                p.setAmount(amount);
                BigDecimal bigDecimalSum = countSum(cart);
                cart.setSum(bigDecimalSum);
                cartDao.addCart(cart);
                break;
            }
        }
        return cartDao.addCart(cart);
    }

    @Override
    public Cart deleteProductFromCart(Long cartId, Long productId) {

        if (productId == null) {
            throw new RuntimeException("Required parameters: productId");
        }
        Cart cart = cartDao.getCart(cartId);
        for (Product p : cart.getProducts()) {
            if (productId.equals(p.getId())) {
                p.setAmount((p.getAmount()) - 1);
                BigDecimal bigDecimalSum = countSum(cart);
                cart.setSum(bigDecimalSum);
                cartDao.addCart(cart);
                break;
            }
        }
        return cartDao.addCart(cart);
    }

    @Override
    public Cart deleteAllProductsFromCart(Long cartId) {
        Cart cart = cartDao.getCart(cartId);
        cart.getProducts().clear();
        BigDecimal bigDecimalSum = BigDecimal.ZERO;
        cart.setSum(bigDecimalSum);
        return cartDao.addCart(cart);
    }

    public BigDecimal countSum(Cart cart) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Product p : cart.getProducts()) {
            sum = sum.add(p.getPrice().multiply(BigDecimal.valueOf(p.getAmount())));
        }
        cart.setSum(sum);
        return sum;
    }

}

