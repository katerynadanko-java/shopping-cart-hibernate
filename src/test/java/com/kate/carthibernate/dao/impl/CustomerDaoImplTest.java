package com.kate.carthibernate.dao.impl;

import com.kate.carthibernate.domain.Customer;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerDaoImplTest {

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
    void testCreateCustomer() {
        System.out.println("Running testCreateCustomer...");

        session.beginTransaction();

        Customer customer = new Customer(1L, "Kate", "Brown", "0677886256", "password");
        Long id = (Long) session.save(customer);

        session.getTransaction().commit();

        Assertions.assertTrue(id > 0);
    }

    @Test
    public void testUpdateCustomer() {
        System.out.println("Running testUpdateCustomer...");

        session.beginTransaction();
        Long id = 2L;
        Customer customer = session.find(Customer.class, id);
        customer.setName("Liza");
        customer.setSurname("Black");
        session.update(customer);
        session.getTransaction().commit();

        Customer updatedCustomer = session.find(Customer.class, id);

        assertEquals("Liza", updatedCustomer.getName());
    }

    @Test
    public void testGetCustomer() {
        System.out.println("Running testGetCustomer...");

        Long id = 2L;

        Customer customer = session.find(Customer.class, id);

        assertEquals("Liza", customer.getName());
    }

    @Test
    public void testListCustomer() {
        System.out.println("Running testList...");

        Query<Customer> query = session.createQuery("SELECT c FROM Customer c", Customer.class);
        List<Customer> resultList = query.getResultList();

        Assertions.assertFalse(resultList.isEmpty());
    }

    @Test
    public void testDeleteCustomer() {
        System.out.println("Running testDeleteCustomer...");

        Long id = 3L;
        Customer customer = session.find(Customer.class, id);

        session.beginTransaction();
        session.delete(customer);
        session.getTransaction().commit();

        Customer deletedCustomer = session.find(Customer.class, id);

        Assertions.assertNull(deletedCustomer);
    }
}