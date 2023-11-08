package com.cognizant.bms.customer.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@DiscriminatorValue("EducationLoan")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducationLoan extends Loan{

    private double courseFee;
    private String courseName;
    private String fatherName;
    private String fatherOccupation;
    private double annualIncome;


}
