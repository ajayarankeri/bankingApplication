package com.hcl.bankingApplication.service;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.bankingApplication.dto.TransactionDto;
import com.hcl.bankingApplication.entity.Account;
import com.hcl.bankingApplication.entity.Customer;
import com.hcl.bankingApplication.entity.Transaction;
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
		
		System.out.println(transactionDto.getTransactionAount()+"  "+transactionDto.getTransactionType());
		// deposit
		if(transactionDto.getTransactionType().equalsIgnoreCase("CR")) {
			transaction=new Transaction();
			BeanUtils.copyProperties(transactionDto, transaction);
			transaction.setTransactionDate(LocalDate.now());
			transaction.setCustId(accountDetails.getCustId());
			transactionRepository.save(transaction);
			
			accountDetails.setBalance(accountDetails.getBalance()+transactionDto.getTransactionAount());
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
					transaction.setTransactionDate(LocalDate.now());
					transaction.setCustId(accountDetails.getCustId());
					transactionRepository.save(transaction);
					
					accountDetails.setBalance(accountDetails.getBalance()-transactionDto.getTransactionAount());
					accountRepository.save(accountDetails);
					transactionStatus="Your transaction success : amount credited";
				}
				
		}
		
		
		
		return transactionStatus;
		
		
		
	}

	public Account validateCustomerDetails(Long custId) {
		 Customer customer=customerReposistory.findByCustId(custId);
		 return accountRepository.findByCustId(customer);
	}

}
