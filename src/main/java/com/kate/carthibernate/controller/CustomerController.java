package com.kate.carthibernate.controller;

import com.kate.carthibernate.domain.Customer;
import com.kate.carthibernate.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("api/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Transactional
    @PostMapping(value = "/create")
    public ResponseEntity createCustomer(@RequestBody Customer customer) {
        log.debug("Start to create customer ", customer);
        customerService.createCustomer(customer);
        return ResponseEntity.ok(customer);
    }

    @GetMapping(value = "/findById/{id}")
    public ResponseEntity<Customer> findById(@PathVariable("id") Long id) {
        log.debug("Start to find Customer with id ", id);
        Customer customer = customerService.getCustomer(id);
        log.debug("Found Customer with id ", customer.getId());
        return ResponseEntity.ok(customer);
    }

    @GetMapping("get")
    public ResponseEntity<List<Customer>> getAll() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @Transactional
    @PutMapping("update/{customerId}/{name}/{surname}")
    public ResponseEntity<Customer> update(@PathVariable Long customerId, @PathVariable String name, @PathVariable String surname) throws IOException {
        log.debug("Start to update customer with id ", customerId);
        return ResponseEntity.ok(customerService.updateCustomer(customerId, name, surname));
    }

    @Transactional
    @DeleteMapping("delete/{customerId}")
    public ResponseEntity<String> delete(@PathVariable Long customerId) {
        log.debug("Start to delete Customer with id ", customerId);
        return ResponseEntity.ok(customerService.deleteCustomer(customerId));
    }
}
