package com.hcl.bankingApplication.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.bankingApplication.dto.TransactionDto;
import com.hcl.bankingApplication.entity.Account;
import com.hcl.bankingApplication.entity.Customer;
import com.hcl.bankingApplication.entity.Transaction;
import com.hcl.bankingApplication.exception.ResourceNotFoundException;
import com.hcl.bankingApplication.repository.AccountRepository;
import com.hcl.bankingApplication.repository.CustomerReposistory;
import com.hcl.bankingApplication.repository.TransactionRepository;


@Service
public class TransactionService {
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	CustomerReposistory customerReposistory;
	
	

	public String makeTransaction(TransactionDto transactionDto,Account accountDetails) {
		Transaction transaction;
		String transactionStatus = null;
		
		// deposit
		if(transactionDto.getTransactionType().equalsIgnoreCase("CR")) {
			transaction=new Transaction();
			BeanUtils.copyProperties(transactionDto, transaction);
			Double updatedBalance=accountDetails.getBalance()+transactionDto.getTransactionAount();
			transaction.setTransactionDate(LocalDate.now());
			transaction.setCustomerId(accountDetails.getCustomerId());
			transaction.setBalance(updatedBalance);
			transactionRepository.save(transaction);
			
			accountDetails.setBalance(updatedBalance);
			accountRepository.save(accountDetails);	
			transactionStatus="Your transaction success : amount deposited";
		}
		// withdraw
		if(transactionDto.getTransactionType().equalsIgnoreCase("DR")) {			
		
			if(accountDetails.getBalance()<transactionDto.getTransactionAount()) {
					System.out.println("insufficient balance for withdraw");
					transactionStatus="Sorry, You dont have insufficient balance for transaction";
				}else {
					transaction=new Transaction();
					BeanUtils.copyProperties(transactionDto, transaction);
					Double updatedBalance=accountDetails.getBalance()-transactionDto.getTransactionAount();
					transaction.setTransactionDate(LocalDate.now());
					transaction.setCustomerId(accountDetails.getCustomerId());
					transaction.setBalance(updatedBalance);
					transactionRepository.save(transaction);
					
					accountDetails.setBalance(updatedBalance);
					accountRepository.save(accountDetails);
					transactionStatus="Your transaction success : amount credited";
				}
				
		}
		
		
		
		return transactionStatus;
		
		
	}

	public Account validateCustomerDetails(Long custId) throws ResourceNotFoundException {
		 Customer customer=customerReposistory.findById(custId).orElseThrow(()-> new ResourceNotFoundException("Please check customer id, provided customer id does not exist!!"));
		 return accountRepository.findByCustomerId(customer);
	}
	
	public List<Transaction> getAllTransaction(long customerId,String fromDate,String toDate) throws ResourceNotFoundException{
		Customer customerObject=customerReposistory.findById(customerId).orElseThrow(()->new ResourceNotFoundException("Customer not found"));
		return transactionRepository.findByCustomerIdAndTransactionDateGreaterThanEqualAndTransactionDateLessThanEqual(customerObject, LocalDate.parse(fromDate), LocalDate.parse(toDate));
	}
}
