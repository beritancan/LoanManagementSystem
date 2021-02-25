package com.example.turkcellLoanSystem.business.abstracts;

import java.util.List;

import com.example.turkcellLoanSystem.entities.concretes.Loan;

public interface ILoanService {
	
	String add(Loan loan);
	String loanApprove(String tcNo); 
	String loanApproveSecond(String tcNo); 
	List<Loan> showLoanToCustomerForApprove(String tcNo);
	String loanToCustomerForApprove(String tcNo, boolean isApprove);
}
