package com.example.turkcellLoanSystem.dataaccess.concretes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.turkcellLoanSystem.entities.concretes.RejectedLoan;

public interface RejectedLoanRepository extends JpaRepository<RejectedLoan, Integer> {
	
	@Query(value = "SELECT * FROM rejected_loans where tc_No = :tcNo"  , nativeQuery = true)     
	  public List<RejectedLoan> rejectedLoanMonitoringForCustomer(@Param("tcNo") String tcNo);
    
	@Query(value = "SELECT * FROM rejected_loans where loan_id = :loanId"  , nativeQuery = true)     
	  public List<RejectedLoan> getLoanById(@Param("loanId") int loanId);

	
}
