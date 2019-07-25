package com.hcl.bankingApplication.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@Entity
@Table(name="transaction")
@Data
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="trans_id")
	@JsonIgnore
	private Long transId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="custId")
	private Customer custId;
	
	@Column(name="description")
	private String description;
	
	@Column(name="transaction_type")
	private String transactionType;
	
	@Column(name="transaction_amount")
	private String transactionAount;
	
	@Column(name="transaction_date")
	private String transactionDate;
	
	

}