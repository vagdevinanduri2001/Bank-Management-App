package com.cognizant.bms.loanms.controller;

import com.cognizant.bms.loanms.entity.Loan;
import com.cognizant.bms.loanms.feign.CustomerFeign;
import com.cognizant.bms.loanms.service.LoanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
@CrossOrigin("http://localhost:3000")
public class LoanController {

    Logger logger = LoggerFactory.getLogger(LoanController.class);

    @Autowired
    private LoanService loanService;

    @PostMapping("/applyLoan")
    public ResponseEntity<?> applyLoan(@RequestHeader("Authorization") String token, @RequestBody Loan loan){
        Loan loan1=loanService.applyLoan(token,loan);

        return new ResponseEntity<>("Loan is granted!", HttpStatus.CREATED);
    }

    @GetMapping("/getLoansByUsername/{userName}")
    public List<Loan> getLoansByUsername(@PathVariable String userName){
        return loanService.findAllByUserName(userName);
    }
}
