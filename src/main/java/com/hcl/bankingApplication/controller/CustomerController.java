package com.hcl.bankingApplication.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.bankingApplication.dto.CustomerDto;
import com.hcl.bankingApplication.entity.Customer;
import com.hcl.bankingApplication.repository.CustomerRepository;
import com.hcl.bankingApplication.service.CustomerService;


@RestController
@RequestMapping("/register")
public class CustomerController {
	
	
	
	@Autowired
	CustomerService customerService;
	
	
	@PostMapping("")
	public ResponseEntity<String> registerUser(@Valid @RequestBody CustomerDto customerDto) {
		
		return new ResponseEntity<>("Hello , You are registered successfully. Your Customer Id is  "+customerService.registerUser(customerDto)+"",HttpStatus.OK);
		
	}
	

}
