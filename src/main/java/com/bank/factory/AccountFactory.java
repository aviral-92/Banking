package com.bank.factory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.bank.service.AccountService;

@Component
public class AccountFactory {

	@Autowired
	private List<AccountService> accountServices;

	public AccountService getAccountService(String accountType) {

		AccountService tempCustomerService = null;
		for (AccountService accountService : accountServices) {
			if (!StringUtils.isEmpty(accountType)) {
				if (accountService.getAccountEnum().toString().equalsIgnoreCase(accountType)) {
					tempCustomerService = accountService;
					break;
				} else if (accountService.getAccountEnum().toString().equalsIgnoreCase(accountType)) {
					tempCustomerService = accountService;
					break;
				}
			}
		}
		return tempCustomerService;
	}
}
