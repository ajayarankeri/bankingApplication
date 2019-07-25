package com.hcl.bankingApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.bankingApplication.entity.Customer;

public interface CustomerReposistory extends JpaRepository<Customer, Long>{

	Customer findByCustId(Long custId);

}
