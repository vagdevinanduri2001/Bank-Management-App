package com.cognizant.bms.loanms.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "loanType",discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,include = JsonTypeInfo.As.PROPERTY,property = "loanType")
@JsonSubTypes({
        @JsonSubTypes.Type(value =HomeLoan.class ,name = "HomeLoan"),
        @JsonSubTypes.Type(value = EducationLoan.class,name = "EducationLoan"),
        @JsonSubTypes.Type(value = PersonalLoan.class,name = "PersonalLoan")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int Id;
    private String userName;
    private int loanAmount;
    private LocalDate applyDate;
    private int rateOfInterest;
    private int durationOfLoan;
}
