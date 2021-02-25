package com.example.turkcellLoanSystem.api.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.turkcellLoanSystem.business.abstracts.ICustomerService;
import com.example.turkcellLoanSystem.entities.concretes.Customer;

@RestController
@RequestMapping("/api/v2")
public class CustomerController {
	
	@Autowired
	ICustomerService customerService;

	@GetMapping("/customers/{id}")
	public Customer getById(@PathVariable String tcNo) throws Exception {
		
		Customer customerToFind = customerService.getById(tcNo);
			
		if (customerToFind == null) {
			return null;
		}
		return customerToFind;
	}
	
	
	@PostMapping("/customers")
	public String add (@RequestBody Customer customer) {
		System.out.println(customer);
		return customerService.add(customer);
	
	}
	
	@PutMapping("/customers/{id}")
	public ResponseEntity<Customer> update(@PathVariable(value="id") String tcNo, @RequestBody Customer customer) throws Exception {
		
		  Customer customerToUpdate = customerService.getById(tcNo);
			
		  customerToUpdate.setTcNo(customer.getTcNo());
		  customerToUpdate.setName(customer.getName());
		  customerToUpdate.setLastName(customer.getLastName());
		  customerToUpdate.setMsisdn(customer.getMsisdn());
		  customerToUpdate.setAddress(customer.getAddress());
		  customerToUpdate.setDateOfBirth(customer.getDateOfBirth());
		  customerToUpdate.setTypeOfCustomer(customer.getTypeOfCustomer());
		  customerToUpdate.setDateOfSubscription(customer.getDateOfSubscription());
		  
		  
		ResponseEntity<Customer> updatedCustomer = customerService.update(customerToUpdate);
		
			return updatedCustomer;
		}

	
	@DeleteMapping("customers/{id}")
	public String delete(@PathVariable(value="id") String tcNo) {
		return customerService.delete(tcNo);
		  }
}
