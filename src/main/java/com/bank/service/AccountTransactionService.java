package com.bank.service;

import com.bank.entity.AccountTransaction;

public interface AccountTransactionService {

	public String amountCredit(AccountTransaction accountTransaction);

	public Double checkBalance(String accountNumber);
}
