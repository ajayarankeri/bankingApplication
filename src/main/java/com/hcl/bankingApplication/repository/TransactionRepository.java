package com.hcl.bankingApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.bankingApplication.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
