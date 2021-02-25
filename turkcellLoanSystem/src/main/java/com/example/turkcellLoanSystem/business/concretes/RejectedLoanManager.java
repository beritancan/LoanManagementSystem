package com.example.turkcellLoanSystem.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.turkcellLoanSystem.business.abstracts.IRejectedLoanService;
import com.example.turkcellLoanSystem.dataaccess.concretes.LoanRepository;
import com.example.turkcellLoanSystem.dataaccess.concretes.RejectedLoanRepository;
import com.example.turkcellLoanSystem.entities.concretes.Loan;
import com.example.turkcellLoanSystem.entities.concretes.RejectedLoan;

@Service
public class RejectedLoanManager implements IRejectedLoanService{

	@Autowired
	RejectedLoanRepository rejectedLoanRepository;
	
	@Autowired
	LoanRepository loanRepository;
	
	@Override
	public String add(String tcNo, String rejectMessage) {
		List<Loan> loan = loanRepository.approveForLoan(tcNo);
		if(loan.size()>0) {
			RejectedLoan rejectLoan = new RejectedLoan();
			rejectLoan.setTcNo(loan.get(0).getTcNo());
			rejectLoan.setDateOfLoan(loan.get(0).getDateOfLoan());
			rejectLoan.setAmountOfLoan(loan.get(0).getAmountOfLoan());
			rejectLoan.setRejectMessage(rejectMessage);
			
			rejectedLoanRepository.save(rejectLoan);
			loanRepository.delete(loan.get(0));
			return "Kredi reddedildi!";
		}else {
			List<Loan> loanSecondApprove =  loanRepository.approveForLoanSecondPhase(tcNo);
			if(loanSecondApprove.size()>0) {
				RejectedLoan rejectLoanOnSecondPhase = new RejectedLoan();
				rejectLoanOnSecondPhase.setTcNo(loanSecondApprove.get(0).getTcNo());
				rejectLoanOnSecondPhase.setDateOfLoan(loanSecondApprove.get(0).getDateOfLoan());
				rejectLoanOnSecondPhase.setAmountOfLoan(loanSecondApprove.get(0).getAmountOfLoan());
				rejectLoanOnSecondPhase.setRejectMessage(rejectMessage);
				
				rejectedLoanRepository.save(rejectLoanOnSecondPhase);
				loanRepository.delete(loanSecondApprove.get(0));
				return "Kredi ikinci aşamada reddedildi!";
				
			} else {
				return "Kredi bulunamadı!";	
			}
		}	
	}

	@Override
	public List<RejectedLoan> loanMonitoring(String tcNo) {
		return rejectedLoanRepository.rejectedLoanMonitoringForCustomer(tcNo);
	}

	@Override
	public String updateLoan(int loanId, int amountOfLoan) {
		List<RejectedLoan> rejectLoan = rejectedLoanRepository.getLoanById(loanId);
		Loan updateLoan = new Loan();
		
		if(rejectLoan.size()>0) {
		updateLoan.setTcNo(rejectLoan.get(0).getTcNo());
		updateLoan.setDateOfLoan(rejectLoan.get(0).getDateOfLoan());
		updateLoan.setAmountOfLoan(amountOfLoan);
		updateLoan.setApprove(3);
	
		loanRepository.save(updateLoan);
		rejectedLoanRepository.delete(rejectLoan.get(0));
		
		return "Kredi bilgileri güncellenip müşteri onayına alınmıştır.";
		}else {
		return "Reddedilen kredi bulunamadı!";
		}
	}

}
