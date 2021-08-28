package com.kate.carthibernate.dao;

import com.kate.carthibernate.domain.Customer;

import java.util.List;

public interface CustomerDao {

    List<Customer> getAllCustomers();

    Customer getCustomer(Long id);

    void addCustomer(Customer customer);

    void updateCustomer(Customer customer);

    void deleteCustomer(Long id);


}
