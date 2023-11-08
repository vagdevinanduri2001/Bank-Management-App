package com.cognizant.bms.customer.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@DiscriminatorValue("HomeLoan")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomeLoan extends Loan{

    private double annualInc;
    private String companyName;
    private String designation;
    private double totalExp;
    private double expWithCc;

}
