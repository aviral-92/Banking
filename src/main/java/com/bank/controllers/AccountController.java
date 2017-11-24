package com.bank.controllers;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.entity.Accounts;
import com.bank.service.AccountService;

@RestController
public class AccountController extends AbstractContoller {

	@Override
	public String openAccount(@RequestBody Accounts accounts) {

		AccountService accountService = getAccountServiceObj(accounts.getAccountType());
		if (!StringUtils.isEmpty(accountService)) {
			return accountService.openAccount(accounts);
		}
		return "Please specify Account type";
	}

	@Override
	public Accounts retrieveAccountInfo(@PathVariable Long accountNumber, @PathVariable String accountType) {

		Accounts accounts = null;
		AccountService accountService = getAccountServiceObj(accountType);
		if (!StringUtils.isEmpty(accountService)) {
			accounts = accountService.accountInfo(accountNumber);
		}
		return accounts;
	}

	@Override
	public String closeAccount(@RequestBody Accounts accounts) {

		String response = null;
		AccountService accountService = getAccountServiceObj(accounts.getAccountType());
		if (!StringUtils.isEmpty(accountService)) {
			response = accountService.closeAccount(accounts);
		}
		return response;
	}

}
