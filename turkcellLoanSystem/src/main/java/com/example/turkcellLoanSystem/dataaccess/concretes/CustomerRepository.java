package com.example.turkcellLoanSystem.dataaccess.concretes;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.turkcellLoanSystem.entities.concretes.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String>{

	@Query(value = "SELECT * FROM turkcell_customers where tc_No = :tcNo" , nativeQuery = true)     
	  public List<Customer> listOfCustomer(@Param("tcNo") String tcNo);
	
	/*
	@Query(value = "SELECT COUNT FROM turkcell_customers where date_of_subscription = :dateOfSubscription " , nativeQuery = true)     
	  public Integer subscriptionPerDay(@Param("dateOfSubscription") Date dateOfSubscription); */
}
