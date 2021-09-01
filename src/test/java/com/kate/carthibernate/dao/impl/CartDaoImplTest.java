package com.kate.carthibernate.dao.impl;

import com.kate.carthibernate.dao.CustomerDao;
import com.kate.carthibernate.domain.Cart;
import com.kate.carthibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class CartDaoImplTest {

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
    void testCreateCart() {
        System.out.println("Running testCreateCart...");

        session.beginTransaction();

        Cart cart = new Cart();

        Long id = (Long) session.save(cart);

        session.getTransaction().commit();

        Assertions.assertTrue(id > 0);
    }

}