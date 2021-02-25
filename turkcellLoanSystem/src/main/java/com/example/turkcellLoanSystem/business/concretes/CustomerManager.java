package com.example.turkcellLoanSystem.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.turkcellLoanSystem.business.abstracts.ICustomerService;
import com.example.turkcellLoanSystem.dataaccess.concretes.CustomerRepository;
import com.example.turkcellLoanSystem.entities.concretes.Customer;

@Service
public class CustomerManager implements ICustomerService{
	
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Customer getById(String tcNo) throws Exception {
		Customer customerToFind = customerRepository.findById(tcNo)
				.orElseThrow(()-> new Exception("Bu TC Kimlik No'lu müşteri bulunamadı: "+ tcNo));
		if (customerToFind == null) {
			return null;
		}
		return customerToFind;
	}

	@Override
	public String add(Customer customer) {
	     System.out.println(customer.getTcNo());
	     
	     List<Customer> updateCustomer = customerRepository.listOfCustomer(customer.getTcNo());
	     System.out.println(updateCustomer);
	     
	     if(updateCustomer.size()>0) { 
	       return "Bu müşteri sistemde mevcuttur, kredi işlemlerine geçebilirsiniz.";
	     } else {
	    	 if(customer.getMsisdn().length()==10) {
	    		 customerRepository.save(customer);
	   		  return "Yeni müşteri başarıyla eklendi.";
	    	 } else {
	    	  return "Telefon numaranızı 10 haneli girmeniz gerekmektedir.";	 
	    	 }
	      
	     
	
        }
	}
	
	@Override
	public ResponseEntity<Customer> update(Customer customer) throws Exception {
		Customer customerToUpdate = customerRepository.findById(customer.getTcNo())
				.orElseThrow(()-> new Exception("Bu TC Kimlik No'lu müşteri bulunamadı: "+ customer.getTcNo()));
	    
		Customer updatedCustomer = customerRepository.save(customerToUpdate);
		
		return ResponseEntity.ok(customerToUpdate); 
	}
	     

	 	@Override
	 public String delete(String tcNo) {
	 		List<Customer> customerDelete = customerRepository.listOfCustomer(tcNo);
		    
			if(customerDelete.size()>0) {
				customerRepository.delete(customerDelete.get(0));
		    	return "Müşteri başarıyla  silindi";
		    } else {
		    	return "Bu TC Kimlik No'lu müşteri bulunamadı";
		    		
		    }
		
	 		
	     
    }
	
	

}



