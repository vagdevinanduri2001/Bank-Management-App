package com.cognizant.bms.customer.service;

import com.cognizant.bms.customer.entity.Customer;

public interface CustomerService {
    String generateAccountNumber();

    boolean checkForAccountNumber(String accountNumber);

    Customer registerCustomer(Customer customer);

    Customer updateCustomer(Customer customer);


    Customer assignLoans(String userName);
}
