package com.cognizant.bms.customer.feign;

import com.cognizant.bms.customer.entity.Loan;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "loan", url = "${LOAN:http://localhost:9091/loan}")
public interface LoanFeign {

    @GetMapping("/getLoansByUsername/{userName}")
    public List<Loan> getLoansByUsername(@PathVariable String userName);

}