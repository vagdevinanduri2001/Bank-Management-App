package com.cognizant.bms.customer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {

    @Id
    private String userName;

    private String password;

    private String name;

    @Email
    private String emailId;

    private String pan;

    private long contact;

    @Temporal(TemporalType.DATE)
    private Date dob;

    private String address;

    private String state;

    private String country;

    private String branchName;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private String accountNo;

    private double initialDeposit;

    private double balance;

    private String identificationProof;

    private String identificationNo;

    @Transient
    private List<Loan> loans;

}
