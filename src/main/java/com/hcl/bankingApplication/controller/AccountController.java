package com.hcl.bankingApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.bankingApplication.dto.TransactionDto;
import com.hcl.bankingApplication.entity.Account;
import com.hcl.bankingApplication.service.TransactionService;

@RestController
@RequestMapping("")
public class AccountController {

	@Autowired
	TransactionService transactionService;
	
	
	@PostMapping("/transaction")
	public String makeTransaction(@RequestBody TransactionDto transaction) {
		String transDetails=null;
		Account accountDetails= transactionService.validateCustomerDetails(transaction.getCustId());
		
		
		
		if(ObjectUtils.isEmpty(accountDetails)) {
			System.out.println("User Account is not created...");
			
		}else {
			
			transDetails=transactionService.makeTransaction(transaction,accountDetails);
			
		}
		return transDetails;
		
	}

}
