package com.example.turkcellLoanSystem.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.turkcellLoanSystem.business.abstracts.ILoanService;
import com.example.turkcellLoanSystem.entities.concretes.Loan;

@RestController
@RequestMapping("/api/v2")
public class LoanController {
	
	@Autowired
	ILoanService loanService;
	
	@PostMapping("/loans")
	public String add (@RequestBody Loan loan) {
		System.out.println(loan);
		return loanService.add(loan);
	}
	
	@PutMapping("/loansFirstApprove/{id}")
	public String loanApprove(@PathVariable(value ="id") String tcNo) {
		return loanService.loanApprove(tcNo);
	}
	
	@PutMapping("/loansSecondApprove/{id}")
	public String loanApproveSecond(@PathVariable(value ="id") String tcNo) {
		return loanService.loanApproveSecond(tcNo);
	}
	
	@GetMapping("/loansShowApprove/{id}")
	public List<Loan> showLoanToCustomerForApprove (@PathVariable(value ="id") String tcNo) {
		return loanService.showLoanToCustomerForApprove(tcNo);
	}
	
	@PutMapping("/loansCustomerApprove/{id}/{isApprove}")
	public String loanToCustomerForApprove (@PathVariable(value ="id") String tcNo, @PathVariable(value ="isApprove" ) boolean approve) {
		return loanService.loanToCustomerForApprove(tcNo, approve);
	}
}
