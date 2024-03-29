package com.hcl.bankingApplication.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.bankingApplication.dto.AccountTransactionDto;
import com.hcl.bankingApplication.dto.TransactionDto;
import com.hcl.bankingApplication.entity.Account;
import com.hcl.bankingApplication.entity.Transaction;
import com.hcl.bankingApplication.exception.ResourceNotFoundException;
import com.hcl.bankingApplication.service.TransactionService;

@CrossOrigin
@RestController
@RequestMapping("/account")
public class AccountController {
	
	static Logger log = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	 TransactionService transactionService;
	
	@PostMapping("/transaction/summary")
	public ResponseEntity<Object> getAllTransaction(@RequestBody AccountTransactionDto accountTransactionDto) throws ResourceNotFoundException
	{
		return new ResponseEntity<>(transactionService.getAllTransaction(accountTransactionDto.getCustomerId(), accountTransactionDto.getFromDate(), accountTransactionDto.getToDate()),HttpStatus.OK);
	}
	
	@PostMapping("/transaction")
	public ResponseEntity<Transaction> makeTransaction(@Valid @RequestBody TransactionDto transaction) throws ResourceNotFoundException {
		Transaction transDetails=null;
		Account accountDetails= transactionService.validateCustomerDetails(transaction.getCustomerId());
		
				
		if(ObjectUtils.isEmpty(accountDetails)) {
			log.debug("User Account is not created");
			throw new ResourceNotFoundException("User Account is not created");			
		}else if((transaction.getTransactionType().equalsIgnoreCase("CR")) ||(transaction.getTransactionType().equalsIgnoreCase("DR"))){
			
			transDetails=transactionService.makeTransaction(transaction,accountDetails);
			
		}else {
			log.debug("Please enter correct transaction type i.e. CR or DR");
			throw new ResourceNotFoundException("Please enter correct transaction type i.e. CR or DR");
		}
		
		return new ResponseEntity<>(transDetails,HttpStatus.OK);
		
	}
	
	@PostMapping("/transaction/history")
	public ResponseEntity<?> getTransactionHistory(@RequestParam(value="customerId") Long customerId) throws ResourceNotFoundException{
		
		List<Transaction> lastTenTransaction= null;
		if(customerId!=null) {
			lastTenTransaction = transactionService.getTransactionHistoryByCustomerId(customerId);
		}
		else {
			log.debug("Not a valid customer Id");
			throw new ResourceNotFoundException("Not a valid customer Id");
		}
		return new ResponseEntity<List<Transaction>>(lastTenTransaction,HttpStatus.OK);
		
	}
	

}
