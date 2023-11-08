package com.cognizant.bms.customer.config;

import com.cognizant.bms.customer.entity.Customer;
import com.cognizant.bms.customer.exception.CustomerAlreadyExistsException;
import com.cognizant.bms.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Customer> customer = customerRepository.findById(username);
        return customer.map(CustomerUserDetails::new)
                .orElseThrow(()->new CustomerAlreadyExistsException("user not found"+customer));

    }
}
