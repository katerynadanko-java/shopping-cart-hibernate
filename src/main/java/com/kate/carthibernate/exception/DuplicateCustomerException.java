package com.kate.carthibernate.exception;

import com.kate.carthibernate.domain.Customer;
import lombok.Data;

@Data
public class DuplicateCustomerException extends RuntimeException {
    private final Customer customer;

    public DuplicateCustomerException(Customer customer) {
        this.customer = customer;
    }
}