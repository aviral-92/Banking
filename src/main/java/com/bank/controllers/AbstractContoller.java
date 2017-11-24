package com.bank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bank.entity.Accounts;
import com.bank.factory.AccountFactory;
import com.bank.service.AccountService;

@RequestMapping("/api")
public abstract class AbstractContoller {

	@Autowired
	protected AccountFactory accountFactory;

	@RequestMapping(value = "/account/open", method = RequestMethod.POST)
	public abstract String openAccount(@RequestBody Accounts accounts);

	@RequestMapping(value = "/account/{accountNumber}/type/{accountType}/info", method = RequestMethod.GET)
	public abstract Accounts retrieveAccountInfo(@PathVariable Long accountNumber, @PathVariable String accountType);

	@RequestMapping(value = "/account/close", method = RequestMethod.POST)
	public abstract String closeAccount(@RequestBody Accounts accounts);

	protected AccountService getAccountServiceObj(String accountType) {

		return accountFactory.getAccountService(accountType);
	}
}
