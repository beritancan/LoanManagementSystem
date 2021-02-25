package com.example.turkcellLoanSystem.dataaccess.concretes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.turkcellLoanSystem.entities.concretes.LoanLimit;


public interface LoanLimitRepository  extends JpaRepository<LoanLimit, String>{

	
	@Query(value = "SELECT * FROM loan_limits where tc_No = :tcNo" , nativeQuery = true)     
	  public List<LoanLimit> listOfLoanLimit(@Param("tcNo") String tcNo);
}
