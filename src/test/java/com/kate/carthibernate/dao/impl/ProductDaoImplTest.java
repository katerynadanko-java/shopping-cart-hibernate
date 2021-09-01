package com.kate.carthibernate.dao.impl;

import com.kate.carthibernate.domain.Product;
import com.kate.carthibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductDaoImplTest {

    private static SessionFactory sessionFactory;
    private Session session;

    @BeforeEach
    public void openSession() {
        session = sessionFactory.openSession();
        System.out.println("Session created");
    }

    @AfterEach
    public void closeSession() {
        if (session != null) session.close();
        System.out.println("Session closed\n");
    }

    @BeforeAll
    public static void setup() {
        sessionFactory = HibernateUtil.getSessionFactory();
        System.out.println("SessionFactory created");
    }

    @AfterAll
    public static void tearDown() {
        if (sessionFactory != null) sessionFactory.close();
        System.out.println("SessionFactory destroyed");
    }

    @Test
    void testCreateProduct() {
        System.out.println("Running testCreateProduct...");

        session.beginTransaction();
        Product product = new Product(1L, "Mac Book", "Nice", new BigDecimal(234), 4);
        Long id = (Long) session.save(product);
        session.getTransaction().commit();
        Assertions.assertTrue(id > 0);
    }

    @Test
    public void testUpdateCustomer() {
        System.out.println("Running testUpdateCustomer...");
        session.beginTransaction();
        Long id = 2L;
        Product product = session.find(Product.class, id);
        product.setPrice(new BigDecimal(534));
        session.update(product);
        session.getTransaction().commit();
        Product updatedProduct = session.find(Product.class, id);

        assertEquals(new BigDecimal(534), updatedProduct.getPrice());
    }

    @Test
    public void testGetProduct() {
        System.out.println("Running testUpdateCustomer...");
        Long id = 3L;
        Product product = session.find(Product.class, id);
        assertEquals("TV", product.getName());
    }

    @Test
    public void testListProduct() {
        System.out.println("Running testListProduct...");

        Query<Product> query = session.createQuery("SELECT c FROM Product c", Product.class);
        List<Product> resultList = query.getResultList();

        Assertions.assertFalse(resultList.isEmpty());
    }

    @Test
    public void testDeleteProduct() {
        System.out.println("Running testDeleteProduct...");

        Long id = 4L;
        Product product = session.find(Product.class, id);

        session.beginTransaction();
        session.delete(product);
        session.getTransaction().commit();

        Product deletedProduct = session.find(Product.class, id);

        Assertions.assertNull(deletedProduct);
    }
}