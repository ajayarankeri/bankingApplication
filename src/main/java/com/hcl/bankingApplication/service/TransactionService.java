package com.hcl.bankingApplication.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.hcl.bankingApplication.repository.CustomerRepository;
import com.hcl.bankingApplication.repository.TransactionRepository;
import com.hcl.bankingApplication.entity.Customer;
import com.hcl.bankingApplication.entity.Transaction;
import com.hcl.bankingApplication.exception.ResourceNotFoundException;


@Service
public class TransactionService {

	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	public List<Transaction> getAllTransaction(long customerId,String fromDate,String toDate) throws ResourceNotFoundException{
		Customer customerObject=customerRepository.findById(customerId).orElseThrow(()->new ResourceNotFoundException("Customer not found"));	
		return transactionRepository.findByCustomerIdAndTransactionDateGreaterThanAndTransactionDateLessThan(customerObject, LocalDate.parse(fromDate), LocalDate.parse(toDate));
	}
}
