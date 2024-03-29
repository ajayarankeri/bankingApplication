package com.hcl.bankingApplication.service;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.bankingApplication.dto.CustomerDto;
import com.hcl.bankingApplication.entity.Account;
import com.hcl.bankingApplication.entity.Customer;
import com.hcl.bankingApplication.repository.AccountRepository;
import com.hcl.bankingApplication.repository.CustomerRepository;


@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository  customerRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	public long registerUser(CustomerDto customerDto) {
		Customer customer=new Customer();
		
		BeanUtils.copyProperties(customerDto, customer);
		
		
		Customer customerObj = new Customer();
		customerObj =	customerRepository.save(customer);
		
		Account account =new Account();
		int number=(int)((Math.random()*9000000)+1000000);
		//double d = Double.parseDouble(number);
		
		account.setCustomerId(customerObj);
		account.setAccountNumber(Long.valueOf(number));
		account.setAccountType("Saving");
		account.setBalance(Double.valueOf(20000));
		accountRepository.save(account);
		return  customerObj.getCustomerId();
	}
	
	

}
