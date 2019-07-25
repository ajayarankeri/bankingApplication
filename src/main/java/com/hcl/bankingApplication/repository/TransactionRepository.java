package com.hcl.bankingApplication.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hcl.bankingApplication.entity.Customer;
import com.hcl.bankingApplication.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	public List<Transaction> findByCustomerIdAndTransactionDateGreaterThanEqualAndTransactionDateLessThanEqual(Customer customer,LocalDate fromDate,LocalDate toDate);
}
