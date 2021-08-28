package com.kate.carthibernate.dao.impl;

import com.kate.carthibernate.dao.CustomerDao;
import com.kate.carthibernate.domain.Customer;
import com.kate.carthibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerDaoImpl implements CustomerDao {

    @Override
    public void addCustomer(Customer customer) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(customer);
        transaction.commit();
        session.close();
    }

    @Override
    public void updateCustomer(Customer customer) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(customer);
        transaction.commit();
        session.close();
    }

    @Override
    public Customer getCustomer(Long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = session.byId(Customer.class).load(id);
        transaction.commit();
        session.close();
        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Customer> customers = session.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
        transaction.commit();
        session.close();
        return customers;
    }

    @Override
    public void deleteCustomer(Long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(getCustomer(id));
        transaction.commit();
        session.close();
    }
}
