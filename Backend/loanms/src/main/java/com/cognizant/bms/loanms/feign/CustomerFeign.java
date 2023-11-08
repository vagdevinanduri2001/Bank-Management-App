package com.cognizant.bms.loanms.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "customer", url = "${CUSTOMER:http://localhost:9090/customer}")
public interface CustomerFeign {
//    @GetMapping("/findByUsername/{userName}")
//    public Customer findByUsername(@RequestHeader("Authorization")String token, @PathVariable String userName);

    @GetMapping("/getUsernameFromToken")
    public String getUsernameFromToken(@RequestHeader("Authorization") String token);
}
