package com.cognizant.bms.loanms.service;

import com.cognizant.bms.loanms.entity.Loan;
import com.cognizant.bms.loanms.feign.CustomerFeign;
import com.cognizant.bms.loanms.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService{
    @Autowired
    private CustomerFeign customerFeign;
    @Autowired
    private LoanRepository loanRepository;

    @Override
    public Loan applyLoan(String token, Loan loan) {
        String userName = customerFeign.getUsernameFromToken(token);
        loan.setUserName(userName);

        loan.setApplyDate(LocalDate.now());

        return loanRepository.save(loan);
    }

    @Override
    public List<Loan> findAllByUserName(String userName){
       return loanRepository.findByUserName(userName);
    }
}
