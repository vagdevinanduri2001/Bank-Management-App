package com.cognizant.bms.customer.service;

import com.cognizant.bms.customer.entity.Customer;
import com.cognizant.bms.customer.entity.Loan;
import com.cognizant.bms.customer.exception.CustomerAlreadyExistsException;
import com.cognizant.bms.customer.feign.LoanFeign;
import com.cognizant.bms.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private LoanFeign loanFeign;

    @Override
    public String generateAccountNumber() {
        return String.valueOf(Math.abs(new Random().nextLong())).substring(0, 10);
    }

    public Customer getByUserName(String userName){
        return customerRepository.findById(userName).get();
    }

    @Override
    public boolean checkForAccountNumber(String accountNumber) {
        return customerRepository.existsByAccountNo(accountNumber);
    }

    public String assignAcc(){
        String acc = generateAccountNumber();

        while(true){

            if(checkForAccountNumber(acc)){
                acc = generateAccountNumber();
            }
            else{
                break;
            }
        }
        return acc;

    }

    @Override
    public Customer registerCustomer(Customer customer) {
        Optional<Customer> check = customerRepository.findById(customer.getUserName());
        if(check.isPresent()){
            throw new CustomerAlreadyExistsException("Customer with this username is already present...please proceed with another username.");
        }
        Customer checkPan = customerRepository.findByPan(customer.getPan());
        if (checkPan!=null){
            throw new CustomerAlreadyExistsException("Customer with this PAN is already present.");
        }
        else{
            customer.setPassword(passwordEncoder.encode(customer.getPassword()));
            customer.setAccountNo(assignAcc());
            customer.setBalance(customer.getInitialDeposit());
           return customerRepository.save(customer);
        }

    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer assignLoans(String userName){
        Optional<Customer> customer = customerRepository.findById(userName);
        List<Loan> loans = loanFeign.getLoansByUsername(userName);
        System.out.println(loans);
        customer.get().setLoans(loans);
        return customerRepository.save(customer.get());
    }


}
