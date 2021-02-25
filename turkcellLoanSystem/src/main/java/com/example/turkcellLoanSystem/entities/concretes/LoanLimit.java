package com.example.turkcellLoanSystem.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.turkcellLoanSystem.entities.abstracts.IEntity;

import lombok.Data;

@Data
@Entity
@Table(name="loan_limits")
public class LoanLimit implements IEntity {
	
	@Id
	@Column(name="tc_No")
	private String tcNo;
	@Column(name="loan_amount_at_once")
	private int loanAmountAtOnce;
	@Column(name="loan_amount_per_year")
	private int loanAmountPerYear;

}
