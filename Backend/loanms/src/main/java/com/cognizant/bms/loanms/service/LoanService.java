package com.cognizant.bms.loanms.service;

import com.cognizant.bms.loanms.entity.Loan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LoanService {

    Loan applyLoan(String token, Loan loan);

    List<Loan> findAllByUserName(String userName);
}
