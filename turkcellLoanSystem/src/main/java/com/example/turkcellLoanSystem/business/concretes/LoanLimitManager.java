package com.example.turkcellLoanSystem.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.turkcellLoanSystem.business.abstracts.ILoanLimitService;
import com.example.turkcellLoanSystem.dataaccess.concretes.LoanLimitRepository;
import com.example.turkcellLoanSystem.entities.concretes.LoanLimit;

@Service
public class LoanLimitManager implements ILoanLimitService{

	@Autowired
	private LoanLimitRepository loanLimitRepository;

	@Override
	public String add(LoanLimit loanLimit) {
		List<LoanLimit> updateLimitList = loanLimitRepository.listOfLoanLimit(loanLimit.getTcNo());
		
		if(updateLimitList.size()>0) {
			LoanLimit updatedLimit = updateLimitList.get(0);
	
			updatedLimit.setLoanAmountAtOnce(loanLimit.getLoanAmountAtOnce());
			updatedLimit.setLoanAmountPerYear(loanLimit.getLoanAmountPerYear());
			
			loanLimitRepository.save(updatedLimit);
			return "Müşterinin limit bilgileri güncellenmiştir";
		} else {
			loanLimitRepository.save(loanLimit);
			return "Müşteri limiti oluşturulmuştur.";
		}
		
	}
	
	
}
