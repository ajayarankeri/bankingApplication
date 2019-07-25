package com.hcl.bankingApplication.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.bankingApplication.dto.AccountTransactionDto;
import com.hcl.bankingApplication.dto.TransactionDto;
import com.hcl.bankingApplication.entity.Account;
import com.hcl.bankingApplication.exception.ResourceNotFoundException;
import com.hcl.bankingApplication.service.TransactionService;

@RestController
@RequestMapping("/account")
public class AccountController {
 
	@Autowired
	 TransactionService transactionService;
	
	@PostMapping("/transaction/summary")
	public ResponseEntity<Object> getAllTransaction(@RequestBody AccountTransactionDto accountTransactionDto) throws ResourceNotFoundException
	{
		return new ResponseEntity<>(transactionService.getAllTransaction(accountTransactionDto.getCustomerId(), accountTransactionDto.getFromDate(), accountTransactionDto.getToDate()),HttpStatus.OK);
	}
	
	@PostMapping("/transaction")
	public String makeTransaction(@Valid @RequestBody TransactionDto transaction) throws ResourceNotFoundException {
		String transDetails=null;
		Account accountDetails= transactionService.validateCustomerDetails(transaction.getCustomerId());
		
				
		if(ObjectUtils.isEmpty(accountDetails)) {
			System.out.println("User Account is not created...");
			
		}else {
			
			transDetails=transactionService.makeTransaction(transaction,accountDetails);
			
		}
		return transDetails;
		
	}

}
