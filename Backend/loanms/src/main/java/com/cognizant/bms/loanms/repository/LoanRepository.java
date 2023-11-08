package com.cognizant.bms.loanms.repository;

import com.cognizant.bms.loanms.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan,Integer> {

    List<Loan> findByUserName(String userName);
}
