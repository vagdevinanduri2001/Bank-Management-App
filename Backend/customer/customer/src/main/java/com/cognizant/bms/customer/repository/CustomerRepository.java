package com.cognizant.bms.customer.repository;

import com.cognizant.bms.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,String> {
    Customer findByPan(String pan);
    boolean existsByAccountNo(String accountNo);
}
