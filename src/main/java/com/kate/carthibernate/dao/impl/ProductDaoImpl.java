package com.kate.carthibernate.dao.impl;

import com.kate.carthibernate.dao.ProductDao;
import com.kate.carthibernate.domain.Product;
import com.kate.carthibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDaoImpl implements ProductDao {

    public void addProduct(Product product) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(product);
        transaction.commit();
        session.close();
    }

    @Override
    public Product getProduct(Long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Product product = session.byId(Product.class).load(id);
        session.close();
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Product> products = session.createQuery("SELECT p FROM Product p", Product.class).getResultList();
        session.close();
        return products;
    }

    @Override
    public void deleteProduct(Long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(getProduct(id));
        transaction.commit();
        session.close();
    }
}

