package com.bank.service.impl;

import org.springframework.stereotype.Service;

import com.bank.entity.Accounts;
import com.bank.enums.AccountEnum;
import com.bank.service.AccountService;

@Service
public class AccountServiceCurrentImpl implements AccountService {

	@Override
	public String openAccount(Accounts accounts) {
		return "Current Account";
	}

	@Override
	public Accounts accountInfo(Long accountNumber) {
		return null;
	}

	public String closeAccount(Accounts accounts) {

		String response = null;
		return response;
	}

	@Override
	public AccountEnum getAccountEnum() {
		return AccountEnum.CURRENT_ACCOUNT;
	}

}
