package com.example.turkcellLoanSystem.entities.concretes;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.turkcellLoanSystem.entities.abstracts.IEntity;

import lombok.Data;

@Data
@Entity
@Table(name="loans")
public class Loan implements IEntity {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="loan_id")
	private int loanId; 
	@Column(name="tc_No")
	private String tcNo;
	@Column(name="date_of_loan")
	private Date dateOfLoan;
	@Column(name="amount_of_loan")
	private int amountOfLoan;
	@Column(name="approve")
	private int approve;
	
}

