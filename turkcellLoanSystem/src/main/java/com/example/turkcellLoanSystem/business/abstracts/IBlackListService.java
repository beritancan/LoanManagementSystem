package com.example.turkcellLoanSystem.business.abstracts;


import com.example.turkcellLoanSystem.entities.concretes.BlackList;


public interface IBlackListService {
	
	String add(BlackList blackList);
	String delete(String tcNo);
}
