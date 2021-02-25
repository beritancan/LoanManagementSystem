package com.example.turkcellLoanSystem.business.abstracts;

import org.springframework.http.ResponseEntity;

import com.example.turkcellLoanSystem.entities.concretes.Customer;

public interface ICustomerService {
	
	Customer getById(String tcNo) throws Exception;
	String add(Customer customer);
	ResponseEntity <Customer> update(Customer customer) throws Exception;
	String delete(String tcNo);

}
