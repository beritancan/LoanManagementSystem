 
package com.example.turkcellLoanSystem.api.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.turkcellLoanSystem.business.abstracts.IRejectedLoanService;
import com.example.turkcellLoanSystem.entities.concretes.RejectedLoan;

@RestController
@RequestMapping("/api/v2")
public class RejectedLoanController {
	
	@Autowired
	IRejectedLoanService rejectedLoanService;
	
	@PostMapping("/rejectedLoans/{id}/{rejectMessage}")
	public String add (@PathVariable(value = "id") String tcNo, @PathVariable(value = "rejectMessage") String rejectMessage) {
		return rejectedLoanService.add(tcNo, rejectMessage);
	}
	
	@GetMapping("/rejectedLoans/{id}")
	public List<RejectedLoan> loanMonitoring( @PathVariable(value = "id") String tcNo) {
		return rejectedLoanService.loanMonitoring(tcNo);
	}
	
	@PostMapping("/rejectedLoansUpdate/{id}/{amount}") 
	public String updateLoan(@PathVariable(value = "id") int loanId, @PathVariable(value = "amount") int amountOfLoan) {
		return rejectedLoanService.updateLoan(loanId, amountOfLoan);
	}
}


