package com.kate.carthibernate.service.impl;

import com.kate.carthibernate.dao.CartDao;
import com.kate.carthibernate.dao.CustomerDao;
import com.kate.carthibernate.dao.ProductDao;
import com.kate.carthibernate.domain.Cart;
import com.kate.carthibernate.domain.Customer;
import com.kate.carthibernate.domain.Product;
import com.kate.carthibernate.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
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
    public Cart createCart(Long customerId) {
        log.debug("Star to create cart with customerId ", customerId);
        Cart cart = new Cart();
        Customer customer = customerDao.getCustomer(customerId);
        Cart savedCart = cartDao.addCart(cart);
        carts.add(cart);

        customer.setCarts(carts);
        customerDao.updateCustomer(customer);
        return savedCart;
    }

    @Override
    public List<Cart> getAllCarts() {
        return cartDao.getAllCarts();
    }

    @Override
    public Cart addProductsToCart(Long cartId, Long productId, Integer amount) {

        log.debug("Star to add products to cart with cartId ", cartId);
        Product product = productDao.getProduct(productId);
        Cart cart = cartDao.getCart(cartId);
        product.setAmount(amount);
        products.add(product);
        cart.setProducts(products);
        BigDecimal bigDecimalSum = countSum(cart);
        cart.setSum(bigDecimalSum);
        cartDao.updateCart(cart);
        return cart;
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
                cartDao.updateCart(cart);
                cart.setSum(bigDecimalSum);
                return cart;
            }
        }
        List<Product> products = cart.getProducts();
        Product product = productDao.getProduct(productId);
        products.add(product);
        cartDao.updateCart(cart);
        return cart;
    }

    @Override
    public Cart deleteProductFromCart(Long cartId, Long productId) {
        log.debug("Star to delete products from cart ", cartId);
        if (productId == null) {
            log.debug("exception");
            throw new RuntimeException("product not exist");
        }
        Cart cart = cartDao.getCart(cartId);

        for (Product p : cart.getProducts()) {
            if (p.getAmount() < 1) {
                p.setAmount(0);
                cartDao.updateCart(cart);
                break;
            }
            if (productId.equals(p.getId())) {
                p.setAmount((p.getAmount()) - 1);
                BigDecimal bigDecimalSum = countSum(cart);
                cart.setSum(bigDecimalSum);
                cartDao.updateCart(cart);
            }
            break;

        }
        return cart;
    }

    @Override
    public Cart deleteAllProductsFromCart(Long cartId) {
        Cart cart = cartDao.getCart(cartId);
        cart.getProducts().clear();
        BigDecimal bigDecimalSum = BigDecimal.ZERO;
        cart.setSum(bigDecimalSum);
        cartDao.updateCart(cart);
        return cart;
    }

    public BigDecimal countSum(Cart cart) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Product p : cart.getProducts()) {
            sum = sum.add(p.getPrice().multiply(BigDecimal.valueOf(p.getAmount())));
        }
        cart.setSum(sum);
        log.debug("count sum" + sum);
        return sum;
    }

}

