package com.hcl.bankingApplication.dto;

import lombok.Data;

@Data
public class TranjactionResponseDto {
	
	private Object transactionResult;
	private double totalCredit;
	private double totalDebit;
	private double totalBalance;

}
