package com.hcl.bankingApplication.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.bankingApplication.dto.TransactionDto;
import com.hcl.bankingApplication.entity.Account;
import com.hcl.bankingApplication.entity.Customer;
import com.hcl.bankingApplication.entity.Transaction;
import com.hcl.bankingApplication.exception.ResourceNotFoundException;
import com.hcl.bankingApplication.repository.AccountRepository;
import com.hcl.bankingApplication.repository.CustomerRepository;
import com.hcl.bankingApplication.repository.TransactionRepository;


@Service
public class TransactionService {
	
	static Logger log = LoggerFactory.getLogger(TransactionService.class);
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	

	public Transaction makeTransaction(TransactionDto transactionDto,Account accountDetails) throws ResourceNotFoundException {
		Transaction transaction = null;
		
		// deposit
		if(transactionDto.getTransactionType().equalsIgnoreCase("CR")) {
			transaction=new Transaction();
			BeanUtils.copyProperties(transactionDto, transaction);
			Double updatedBalance=accountDetails.getBalance()+transactionDto.getTransactionAount();
			transaction.setTransactionDate(LocalDate.now());
			transaction.setCustomerId(accountDetails.getCustomerId());
			transaction.setBalance(updatedBalance);
			transaction.setTransactionType("CR");
			transactionRepository.save(transaction);
			
			accountDetails.setBalance(updatedBalance);
			accountRepository.save(accountDetails);	
		}
		// withdraw
		if(transactionDto.getTransactionType().equalsIgnoreCase("DR")) {			
		
			if(accountDetails.getBalance()<transactionDto.getTransactionAount()) {
					log.debug("Sorry, Your dont have sufficient balance for transaction: For customer Id=" +accountDetails.getCustomerId());
					throw new ResourceNotFoundException("Sorry, Your dont have sufficient balance for transaction!");
				}else {
					transaction=new Transaction();
					BeanUtils.copyProperties(transactionDto, transaction);
					Double updatedBalance=accountDetails.getBalance()-transactionDto.getTransactionAount();
					transaction.setTransactionDate(LocalDate.now());
					transaction.setCustomerId(accountDetails.getCustomerId());
					transaction.setBalance(updatedBalance);
					transaction.setTransactionType("DR");
					transactionRepository.save(transaction);
					
					accountDetails.setBalance(updatedBalance);
					accountRepository.save(accountDetails);
				}			
		}
		
		return transaction;		
	}

	public Account validateCustomerDetails(Long custId) throws ResourceNotFoundException {
		 Customer customer=customerRepository.findById(custId).orElseThrow(()-> new ResourceNotFoundException("Please check customer id, provided customer id does not exist!!"));
		 return accountRepository.findByCustomerId(customer);
	}
	
	public List<Transaction> getAllTransaction(long customerId,String fromDate,String toDate) throws ResourceNotFoundException{
		Customer customerObject=customerRepository.findById(customerId).orElseThrow(()->new ResourceNotFoundException("Customer not found"));	
		return transactionRepository.findByCustomerIdAndTransactionDateGreaterThanAndTransactionDateLessThan(customerObject, LocalDate.parse(fromDate), LocalDate.parse(toDate));
	}
	
	public List<Transaction> getTransactionHistoryByCustomerId(Long customerId) throws ResourceNotFoundException
	{	
		Customer customer = customerRepository.findById(customerId).orElseThrow(()-> new ResourceNotFoundException("resource not found"));
		return transactionRepository.findTop10ByCustomerIdOrderByTransactionDateDesc(customer);
	
	}
}
