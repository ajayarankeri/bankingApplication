package com.hcl.bankingApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.bankingApplication.entity.Customer;

	
	

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

	public Customer findByCustomerId(Long customerId);

	
}
