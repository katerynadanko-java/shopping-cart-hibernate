package com.kate.carthibernate.service;

import com.kate.carthibernate.domain.Customer;

import java.util.List;

public interface CustomerService {
    Customer getCustomer(Long id);

    void createCustomer(Customer customer);

    List<Customer> getAllCustomers();

    void updateCustomer(Long id, String name, String surname);

    String deleteCustomer(Long id);

}
