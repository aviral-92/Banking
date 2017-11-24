package com.bank.service.impl.current;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bank.entity.Accounts;
import com.bank.enums.AccountEnum;
import com.bank.enums.AccountStatusEnum;
import com.bank.repo.AccountRepository;
import com.bank.service.AccountService;

@Service
public class AccountServiceCurrentImpl implements AccountService {

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
						&& !getAccount.getAccountType().equals(AccountEnum.CURRENT_ACCOUNT.name())) {
					accounts.setCustomer(null);
				} else if (getAccount.getAccountType().equals(AccountEnum.CURRENT_ACCOUNT.name())) {
					return "Customer already exist for Current account";
				}
			}
			try {
				account = accountRepository.save(accounts);
				if (!StringUtils.isEmpty(account)) {
					response = "Your Current Account has opened and will be activated within 48hrs. Your account number is "
							+ account.getAccountNumber();
				}
			} catch (Exception e) {
				response = "Customer already Exist...";
				System.err.println("Inside Catch block OpenAccount() in Current");
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

	public String closeAccount(Accounts accounts) {

		String response = null;
		if (!StringUtils.isEmpty(accounts) && !StringUtils.isEmpty(accounts.getAccountAdhaarNumber())) {
			Accounts tempAccount = null;
			List<Accounts> getAccountList = getCustomer(accounts.getAccountAdhaarNumber());
			for (Accounts getAccount : getAccountList) {
				if (AccountEnum.CURRENT_ACCOUNT.name().equals(getAccount.getAccountType())) {
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
						System.err.println("Inside Catch block of AccountServiceCurrentImpl.java");
					}
				}
			}
		} else {
			response = "Please provide valid Account Number.";
		}
		return response;
	}

	@Override
	public AccountEnum getAccountEnum() {
		return AccountEnum.CURRENT_ACCOUNT;
	}

	private List<Accounts> getCustomer(Long adhaarNumber) {

		return accountRepository.findByAccountAdhaarNumber(adhaarNumber);
	}

}
