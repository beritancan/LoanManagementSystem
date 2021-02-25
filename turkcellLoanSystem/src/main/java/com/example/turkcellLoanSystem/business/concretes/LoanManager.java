
package com.example.turkcellLoanSystem.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.turkcellLoanSystem.business.abstracts.ILoanService;
import com.example.turkcellLoanSystem.dataaccess.concretes.BlackListRepository;
import com.example.turkcellLoanSystem.dataaccess.concretes.CustomerRepository;
import com.example.turkcellLoanSystem.dataaccess.concretes.LoanLimitRepository;
import com.example.turkcellLoanSystem.dataaccess.concretes.LoanRepository;
import com.example.turkcellLoanSystem.dataaccess.concretes.RejectedLoanRepository;
import com.example.turkcellLoanSystem.entities.concretes.BlackList;
import com.example.turkcellLoanSystem.entities.concretes.Customer;
import com.example.turkcellLoanSystem.entities.concretes.Loan;
import com.example.turkcellLoanSystem.entities.concretes.LoanLimit;
import com.example.turkcellLoanSystem.entities.concretes.RejectedLoan;

@Service
public class LoanManager implements ILoanService {

	
	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private BlackListRepository blackListRepository;
	
	@Autowired
	private LoanLimitRepository loanLimitRepository;
	
	@Autowired
	private RejectedLoanRepository rejectedLoanRepository;
	
	@SuppressWarnings("deprecation")
	@Override
	public String add(Loan loan) {
		List<Customer> customer = customerRepository.listOfCustomer(loan.getTcNo());
		
		if(customer.size()>0) {
			List<BlackList> blackList = blackListRepository.isBlackList(loan.getTcNo());
			if(blackList.size()==0) {
				List<Loan> waitingForApprove = loanRepository.isThereAnyApprovedLoan(loan.getTcNo());
				if(waitingForApprove.size()==0) {
					
				
				List<LoanLimit> loanLimit = loanLimitRepository.listOfLoanLimit(loan.getTcNo());
				if(loanLimit.size()>0) {
					LoanLimit determinedLoanLimit = loanLimit.get(0);
					if(determinedLoanLimit.getLoanAmountAtOnce()>=loan.getAmountOfLoan()) {
					List<Loan> loanList = loanRepository.listOfLoan(loan.getTcNo());
					int usedLimit = 0;
					boolean isThereAnyLoanUsedInThisMonth = false;
					
					for (Loan loanData : loanList ) {
						
							if(loanData.getDateOfLoan().getYear()== loan.getDateOfLoan().getYear()) {
								usedLimit = usedLimit + loanData.getAmountOfLoan();
								if (loanData.getDateOfLoan().getMonth()!= loan.getDateOfLoan().getMonth()) {
							
						}else {
							isThereAnyLoanUsedInThisMonth = true;
						}
					  }
					}
					
					if(isThereAnyLoanUsedInThisMonth==false && usedLimit<determinedLoanLimit.getLoanAmountPerYear()) {
						List<RejectedLoan> rejectLoan = rejectedLoanRepository.rejectedLoanMonitoringForCustomer(loan.getTcNo());
						if(rejectLoan.size()>0) {
							rejectedLoanRepository.delete(rejectLoan.get(0));
							loanRepository.save(loan);
							return "Reddedilen krediniz tekrar onaya alınmıştır.";
						}else {
							loanRepository.save(loan);
							return "Kredi talebiniz başarıyla oluşturulmuştur, onaylanması beklenmektedir.";
						}
					}else if (isThereAnyLoanUsedInThisMonth == true) {
						return "Bu ay kredi kullandığınız için kredi kullanamazsınız!" ;
					} else {
						return "Yıllık kullanılabilecek limitiniz " + determinedLoanLimit.getLoanAmountPerYear() + " TL'dir.Bu limiti aştığınız için kredi kullanamazsınız!";
					}
					}else {
						return "Tek seferde kullanılabilecek maksimum limitiniz " + determinedLoanLimit.getLoanAmountAtOnce() + " TL'dir. Bu limiti aştığınız için kredi kullanamazsınız!";
					}
				}else {
					return "Bu kişiye ait kredi limiti bulunamıştır.Lütfen kredi limitlerini belirleyiniz.";
				}
			 }else { 
				 return "Onaylanmamış krediniz bulunmaktadır, yeni kredi girişi yapamazsınız!";
			 }
			}else {
				return "Bu müşteri kara listededir, kredi kullanamaz!";
			}
		
		}else {
			return "Böyle bir müşteri bulunamamıştır, önce müşteri kaydı oluşturunuz!";
		}
	
	}

	@Override
	public String loanApprove(String tcNo) {
		List<Loan> firstLoanApprove = loanRepository.approveForLoan(tcNo);
		if(firstLoanApprove.size()>0) {
			Loan updateLoan = firstLoanApprove.get(0);
			if(updateLoan.getAmountOfLoan()<=5000) {
				updateLoan.setApprove(2);
				loanRepository.save(updateLoan);
				return "Kredi onaylanmıştır";
			} else {
				updateLoan.setApprove(1);
				loanRepository.save(updateLoan);
				return "Kredi ilk onayı geçmiştir.";
			}
		}else {
			return "Onaylanacak krediniz bulunmamaktadır.";
		}
	}

	@Override
	public String loanApproveSecond(String tcNo) {
		List<Loan> secondLoanApprove = loanRepository.approveForLoanSecondPhase(tcNo);
		if(secondLoanApprove.size()>0) {
			Loan updateLoan = secondLoanApprove.get(0);
				updateLoan.setApprove(2);
				loanRepository.save(updateLoan);
				return "Krediniz onaylanmıştır.";
		}else {
			return "Onaylanacak krediniz bulunmamaktadır.";
		}
	}

	@Override
	public List<Loan> showLoanToCustomerForApprove(String tcNo) {
		
		return loanRepository.showLoanToCustomerForApprove(tcNo);
	}

	@Override
	public String loanToCustomerForApprove(String tcNo, boolean isApprove) {
		List<Loan> updateLoan = loanRepository.showLoanToCustomerForApprove(tcNo);
		if (updateLoan.size()>0) {
			if(isApprove ==  true) {
				updateLoan.get(0).setApprove(0);
				loanRepository.save(updateLoan.get(0));
				return "Kredi güncellemelerini onayladınız, üst yönetimden onay bekleniyor";
			} else {
				loanRepository.delete(updateLoan.get(0));
				return "Kredi güncellemelerini onaylamadınız, yeni kredi işlemi için lütfen yeniden  başvuru yapınız.";
			}
		} else {
		return "Onaylanacak krediniz bulunmamaktadır.";
		}
		
		
	}
}
