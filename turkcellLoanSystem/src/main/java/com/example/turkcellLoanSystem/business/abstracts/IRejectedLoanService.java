package com.example.turkcellLoanSystem.business.abstracts;

import java.util.List;

import com.example.turkcellLoanSystem.entities.concretes.RejectedLoan;

public interface IRejectedLoanService {

	String add(String tcNo, String rejectMessage);
	List<RejectedLoan> loanMonitoring( String tcNo);
	String updateLoan(int loanId, int amountOfLoan);
	
}
