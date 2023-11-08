package com.cognizant.bms.customer.service;

import com.cognizant.bms.customer.entity.AccountType;
import com.cognizant.bms.customer.entity.Customer;
import com.cognizant.bms.customer.entity.EducationLoan;
import com.cognizant.bms.customer.entity.Loan;
import com.cognizant.bms.customer.exception.CustomerAlreadyExistsException;
import com.cognizant.bms.customer.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class CustomerServiceTests {

    @InjectMocks
    private CustomerServiceImpl customerService;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private CustomerRepository customerRepository;

    private Customer customer;

    @BeforeEach
    void setUp() {
        Loan loan = new EducationLoan();
        List<Loan> loans = new ArrayList<>();
        loans.add(loan);
        customer = new Customer("userName", "password", "name",
                "abc@mail.com","pan",999999,new Date(),"address",
                "state","country","branch",AccountType.Savings,"123456",100.0,100.0,
                "aadhar","123456",loans);
    }

    @Test
    public void registerTest_Success(){
        when(customerRepository.save(customer)).thenReturn(customer);
        Customer customer1 = customerService.registerCustomer(customer);
        assertThat(customer1).isNotNull();
    }

    @Test
    public void registerTest_ExistsById(){
        when(customerRepository.findById(customer.getUserName())).thenReturn(Optional.of(customer));
        CustomerAlreadyExistsException e =assertThrows(CustomerAlreadyExistsException.class, () -> {
            customerService.registerCustomer(customer);
        });
        verify(customerRepository, never()).save(any(Customer.class));
        assertEquals("Customer with this username is already present...please proceed with another username.",e.getMessage());
    }

    @Test
    public void registerTest_ExistsByPan(){
        when(customerRepository.findByPan(customer.getPan())).thenReturn(customer);
        CustomerAlreadyExistsException e =assertThrows(CustomerAlreadyExistsException.class, () -> {
            customerService.registerCustomer(customer);
        });
        verify(customerRepository, never()).save(any(Customer.class));
        assertEquals("Customer with this PAN is already present.",e.getMessage());
    }


}
