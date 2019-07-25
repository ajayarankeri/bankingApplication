package com.hcl.bankingApplication.dto;

import lombok.Data;

@Data
public class TransactionDto {

	private Long custId;
	private String description;
	private String transactionType;
	private Double transactionAount;
	
	
	
}
