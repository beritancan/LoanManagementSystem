package com.example.turkcellLoanSystem.business.concretes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.turkcellLoanSystem.business.abstracts.IBlackListService;
import com.example.turkcellLoanSystem.dataaccess.concretes.BlackListRepository;
import com.example.turkcellLoanSystem.dataaccess.concretes.CustomerRepository;
import com.example.turkcellLoanSystem.entities.concretes.BlackList;
import com.example.turkcellLoanSystem.entities.concretes.Customer;

@Service
public class BlackListManager implements IBlackListService {

	@Autowired
	private BlackListRepository blackListRepository;
	
	@Autowired
	private CustomerRepository customerRepository;

	
	@Override
	public String add(BlackList blackList) {

	 List<Customer>	customer = customerRepository.listOfCustomer(blackList.getTcNo());
	 
	 if(customer.size()>0) {
		 blackListRepository.save(blackList);
		 return " Müşteri kara listeye eklendi"; 
	 } else {
		 return "Böyle bir müşteri bulunamadığından kara listeye eklenememiştir.";
	 }
	  
	}

	@Override
	public String delete(String tcNo) {
		List<BlackList> blackListDelete = blackListRepository.isBlackList(tcNo);
	    
		if(blackListDelete.size()>0) {
	    	blackListRepository.delete(blackListDelete.get(0));
	    	return "Müşteri başarıyla kara listeden silindi";
	    } else {
	    	return "Bu TC Kimlik No'lu müşteri kara listede bulunamadı";
	    		
	    }
	
   }




}
