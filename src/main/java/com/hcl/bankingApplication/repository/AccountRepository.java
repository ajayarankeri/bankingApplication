package com.hcl.bankingApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.bankingApplication.entity.Account;
import com.hcl.bankingApplication.entity.Customer;

public interface AccountRepository extends JpaRepository<Account, Long>{

	Account findByCustomerId(Customer customer);

}
