package com.hcl.bankingApplication.dto;

import lombok.Data;

@Data
public class TransactionDto {

	private Long customerId;
	private String description;
	private String transactionType;
	private Double transactionAount;
	
	
	
}
