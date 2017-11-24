package com.bank.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.entity.AccountTransaction;

public interface TransactionRepository extends JpaRepository<AccountTransaction, Integer> {

	public AccountTransaction findByCustomerAccountNumber(String accountNumber);
}
