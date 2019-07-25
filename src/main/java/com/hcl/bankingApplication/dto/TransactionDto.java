package com.hcl.bankingApplication.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class TransactionDto {
	
	
	private Long customerId;
	
	@NotNull(message = "Description should not be null")
	@NotEmpty(message = "Description should not be empty")
	private String description;
	
	@NotNull(message = "Transaction Type should not be null, Should be (CR/DR)")
	@NotEmpty(message = "Transaction Type should not be empty, Should be (CR/DR)")
	private String transactionType;
	
	private Double transactionAount;
	
	
	
}
