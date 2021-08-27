package com.kate.carthibernate.dao;

import com.kate.carthibernate.domain.Customer;
import org.hibernate.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {

     List<Customer> getAllCustomers();

     Customer getCustomer(Long id) ;

     void addCustomer(Customer customer);

     void updateCustomer(Customer customer) ;

     void deleteCustomer(Long id);



}
