package com.kate.carthibernate.service.impl;

import com.kate.carthibernate.dao.CustomerDao;
import com.kate.carthibernate.domain.Customer;
import com.kate.carthibernate.exception.DuplicateCustomerException;
import com.kate.carthibernate.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    @Transactional
    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    @Override
    @Transactional
    public Customer getCustomer(Long id) {
        return customerDao.getCustomer(id);
    }

    @Override
    @Transactional
    public void createCustomer(Customer customer) {
        for (Customer c : customerDao.getAllCustomers()) {
            if (c.equals(customer)) {
                throw new DuplicateCustomerException(customer);
            }
        }
        customerDao.addCustomer(customer);
    }

    @Override
    @Transactional
    public Customer updateCustomer(Long id, String name, String surname) {
        Customer customer = customerDao.getCustomer(id);
        if (name != null) {
            customer.setName(name);
        }
        if (surname != null) {
            customer.setSurname(surname);
        }
        customerDao.updateCustomer(customer);
        return customer;
    }

    @Override
    @Transactional
    public String deleteCustomer(Long id) {
        customerDao.deleteCustomer(id);
        return "Customer " + id + " deleted";
    }
}
