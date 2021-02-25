package com.example.turkcellLoanSystem.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.turkcellLoanSystem.business.abstracts.ILoanLimitService;
import com.example.turkcellLoanSystem.entities.concretes.LoanLimit;

@RestController
@RequestMapping("/api/v2")
public class LoanLimitController {
	
	@Autowired
	ILoanLimitService loanLimitService;
	
	@PostMapping("/loanLimits")
	public String add (@RequestBody LoanLimit loanLimit) {
		System.out.println(loanLimit);
		return loanLimitService.add(loanLimit);
	}
}