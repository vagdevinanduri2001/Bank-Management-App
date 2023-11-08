package com.cognizant.bms.loanms.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("PersonalLoan")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalLoan extends Loan{
    private double annualInc;
    private String companyName;
    private String designation;
    private double totalExp;
    private double expWithCc;
}
