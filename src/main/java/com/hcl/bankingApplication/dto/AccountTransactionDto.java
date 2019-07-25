package com.hcl.bankingApplication.dto;

import lombok.Data;

@Data
public class AccountTransactionDto {
	
	private long customerId;
	private String fromDate;
	private String toDate;

}
