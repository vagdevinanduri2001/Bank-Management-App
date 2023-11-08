package com.cognizant.bms.customer.controller;

import com.cognizant.bms.customer.entity.AuthRequest;
import com.cognizant.bms.customer.entity.AuthResponse;
import com.cognizant.bms.customer.entity.Customer;
import com.cognizant.bms.customer.exception.CustomerAlreadyExistsException;
import com.cognizant.bms.customer.service.CustomerService;
import com.cognizant.bms.customer.service.CustomerServiceImpl;
import com.cognizant.bms.customer.service.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@CrossOrigin("http://localhost:3000")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Customer customer){

        try{
            Customer customer1 = customerService.registerCustomer(customer);
            logger.info("----------------Customer Created------------------");
            return new ResponseEntity<>("Registered Successfully!", HttpStatus.OK);
        }catch(CustomerAlreadyExistsException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        if(authenticate.isAuthenticated()){
            logger.info("----------------Customer Logged in------------------");
            return jwtService. generateToken(authRequest.getUserName());
        }else{
            logger.info("----------------invalid user request !------------------");
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Customer customer){
        Customer customer1 = customerService.updateCustomer(customer);
        logger.info("----------------Updated Successfully!------------------");
        return new ResponseEntity<>("Updated Successfully!",HttpStatus.OK);
    }

    @GetMapping("/findByUsername/{userName}")
    public Customer findByUsername(@PathVariable String userName){
        logger.info("----------------Found customer bt username------------------");
        return customerService.getByUserName(userName);
    }

    @GetMapping("/getUsernameFromToken")
    public String getUsernameFromToken(@RequestHeader("Authorization") String token){
        String userName = jwtService.extractUsername(token.substring(7));
        logger.info("----------------Found customer by token------------------");
        return userName;
    }

    @PutMapping("/assignLoans/{userName}")
    public ResponseEntity<?> assignLoan(@RequestHeader("Authorization") String token,@PathVariable String userName){
        Customer customer = customerService.assignLoans(userName);
        return new ResponseEntity<>("Loans Assigned.",HttpStatus.OK);
    }

    @PutMapping("/assignLoans")
    public ResponseEntity<?> assignLoanWoUserName(@RequestHeader("Authorization") String token){
        String userName = jwtService.extractUsername(token.substring(7));
        Customer customer = customerService.assignLoans(userName);
        System.out.println(customer.getLoans());
        return new ResponseEntity<>("Loans Assigned.",HttpStatus.OK);
    }

}
