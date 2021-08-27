package com.kate.carthibernate.dao.impl;

import com.kate.carthibernate.dao.CartDao;
import com.kate.carthibernate.domain.Cart;
import com.kate.carthibernate.domain.Product;
import com.kate.carthibernate.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CartDaoImpl implements CartDao {

    public Cart addCart(Cart cart) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.save(cart);
        return cart;
    }

    @Override
    public void updateCart(Cart cart) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(cart);
        transaction.commit();
        session.close();
    }
    @Override
    public Cart getCart(Long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        return session.byId(Cart.class).getReference(id);
    }
    @Override
    public List<Cart> getAllCarts() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        return session.createQuery("SELECT c FROM Cart c", Cart.class).getResultList();
    }

    @Override
    public void deleteCart (Long id){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(getCart(id));
        transaction.commit();
        session.close();
    }
}
