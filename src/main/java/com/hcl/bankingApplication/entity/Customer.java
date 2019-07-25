package com.hcl.bankingApplication.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name="customer")
@Data
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cust_id")
	@JsonIgnore
	private Long custId;	
	
	@Column(name="full_name")
	private String fullName;
	
	@Column(name="birth_date")
	private LocalDate birthDate;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="mob_no")
	private String mobNo;
	
	@Column(name="email")
	private String email;
	
	@Column(name="address")
	private String address;
	
	

}
