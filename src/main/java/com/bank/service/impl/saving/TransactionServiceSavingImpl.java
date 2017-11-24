package com.bank.service.impl.saving;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bank.entity.AccountTransaction;
import com.bank.enums.AccountTransactionEnum;
import com.bank.repo.TransactionRepository;
import com.bank.service.AccountTransactionService;

@Service
public class TransactionServiceSavingImpl implements AccountTransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public String amountCredit(AccountTransaction accountTransaction) {

		String response = null;
		if (!StringUtils.isEmpty(accountTransaction)
				&& !StringUtils.isEmpty(accountTransaction.getCustomerAccountNumber())) {
			Double accountBalance = checkBalance(accountTransaction.getCustomerAccountNumber());
			accountBalance = accountBalance + Double.parseDouble(accountTransaction.getCreditAmount());
			accountTransaction.getAccounts().setAccountBalance(String.valueOf(accountBalance));
			accountTransaction.setTransactionStatus(AccountTransactionEnum.CREDIT.name());
			try {
				AccountTransaction transactionCredit = transactionRepository.save(accountTransaction);
				if (!StringUtils.isEmpty(transactionCredit)) {
					response = "Your Amount " + transactionCredit.getCreditAmount()
							+ " is credited and your Reference Number is "
							+ transactionCredit.getTransactionRefrenceNumber();
				}
			} catch (Exception e) {
				response = ("Your transaction failed, please try after sometime");
				System.err.println("In Catch Block TransactionServiceImpl amountCredit() method");
			}
		}
		return response;
	}

	@Override
	public Double checkBalance(String accountNumber) {

		Double response = null;
		AccountTransaction accountTransaction = transactionRepository.findByCustomerAccountNumber(accountNumber);
		if (!StringUtils.isEmpty(accountTransaction) && !StringUtils.isEmpty(accountTransaction.getAccounts())) {
			response = Double.parseDouble(accountTransaction.getAccounts().getAccountBalance());
		}
		return response;
	}
}
