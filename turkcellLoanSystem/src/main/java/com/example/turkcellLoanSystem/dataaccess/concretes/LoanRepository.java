package com.example.turkcellLoanSystem.dataaccess.concretes;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.example.turkcellLoanSystem.entities.concretes.Loan;

public interface LoanRepository extends JpaRepository <Loan, Integer> {
	
	@Query(value = "SELECT * FROM loans where tc_No = :tcNo" , nativeQuery = true)     
	  public List<Loan> listOfLoan(@Param("tcNo") String tcNo);

	@Query(value = "SELECT * FROM loans where tc_No = :tcNo and approve=0"  , nativeQuery = true)     
	  public List<Loan> approveForLoan(@Param("tcNo") String tcNo);
	
	@Query(value = "SELECT * FROM loans where tc_No = :tcNo and approve=1"  , nativeQuery = true)     
	  public List<Loan> approveForLoanSecondPhase(@Param("tcNo") String tcNo);
	
	@Query(value = "SELECT * FROM loans where tc_No = :tcNo and approve=3"  , nativeQuery = true)     
	  public List<Loan> showLoanToCustomerForApprove(@Param("tcNo") String tcNo);
	
	@Query(value = "SELECT * FROM loans where tc_No = :tcNo and (approve=3 or approve=1 or approve=0) "  , nativeQuery = true)     
	  public List<Loan> isThereAnyApprovedLoan(@Param("tcNo") String tcNo);
	
	/*
	@Query(value = "SELECT SUM(amount_of_loan) FROM loans where date_of_loan = :loanDate"  , nativeQuery = true)     
	  public int totalLoanAmountPerDay(@Param("loanDate")Date loanDate); */
}

