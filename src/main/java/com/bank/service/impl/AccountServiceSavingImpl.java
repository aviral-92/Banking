package com.bank.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.bank.entity.Accounts;
import com.bank.enums.AccountEnum;
import com.bank.enums.AccountStatusEnum;
import com.bank.repo.AccountRepository;
import com.bank.service.AccountService;

@Service
@Transactional
public class AccountServiceSavingImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public String openAccount(Accounts accounts) {

		String response = null;
		if (!StringUtils.isEmpty(accounts) && !StringUtils.isEmpty(accounts.getAccountAdhaarNumber())) {
			accounts.setAccountStatus(AccountStatusEnum.PENDING_ACTIVE.name());
			accounts.getCustomer().setStatus(AccountStatusEnum.ACTIVE.name());
			Accounts account = null;
			List<Accounts> getAccountList = getCustomer(accounts.getAccountAdhaarNumber());
			for (Accounts getAccount : getAccountList) {
				if (!StringUtils.isEmpty(getAccount)
						&& !getAccount.getAccountType().equals(AccountEnum.SAVING_ACCOUNT.name())) {
					accounts.setCustomer(null);
				} else if (getAccount.getAccountType().equals(AccountEnum.SAVING_ACCOUNT.name())) {
					return "Customer already exist for saving account";
				}
			}
			try {
				account = accountRepository.save(accounts);
				if (!StringUtils.isEmpty(account)) {
					response = "Your Saving Account has opened and will be activated within 48hrs. Your account number is "
							+ account.getAccountNumber();
				}
			} catch (Exception e) {
				response = "Customer already Exist...";
				System.err.println("Inside Catch block OpenAccount() in Savings");
			}
		}
		return response;
	}

	@Override
	public Accounts accountInfo(Long accountNumber) {

		Accounts account = null;
		if (!StringUtils.isEmpty(accountNumber)) {
			account = accountRepository.findOne(accountNumber);
		}
		return account;
	}

	@Override
	public String closeAccount(Accounts accounts) {

		String response = null;
		if (!StringUtils.isEmpty(accounts) && !StringUtils.isEmpty(accounts.getAccountAdhaarNumber())) {
			Accounts tempAccount = null;
			List<Accounts> getAccountList = getCustomer(accounts.getAccountAdhaarNumber());
			for (Accounts getAccount : getAccountList) {
				if (AccountEnum.SAVING_ACCOUNT.name().equals(getAccount.getAccountType())) {
					tempAccount = new Accounts();
					tempAccount.setAccountNumber(getAccount.getAccountNumber());
					tempAccount.setAccountAdhaarNumber(getAccount.getAccountAdhaarNumber());
					tempAccount.setAccountBalance(getAccount.getAccountBalance());
					tempAccount.setAccountInterestRate(getAccount.getAccountInterestRate());
					tempAccount.setAccountType(getAccount.getAccountType());
					tempAccount.setCustomer(getAccount.getCustomer());
					tempAccount.setAccountStatus(AccountStatusEnum.PENDING_DEACTIVE.name());
					try {
						if (!StringUtils.isEmpty(accountRepository.save(tempAccount))) {
							response = "Account deactivation process has been started and will deactivate within 48hrs.";
						} else {
							response = "Unable to process it";
						}
					} catch (Exception e) {
						response = "Unable to process it";
						System.err.println("Inside Catch block of AccountServiceSavingImpl.java");
					}
				}
			}
		} else {
			response = "Please provide valid Account Number.";
		}
		return response;
	}

	private List<Accounts> getCustomer(Long adhaarNumber) {

		return accountRepository.findByAccountAdhaarNumber(adhaarNumber);
	}

	@Override
	public AccountEnum getAccountEnum() {
		return AccountEnum.SAVING_ACCOUNT;
	}

}
