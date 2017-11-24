package com.bank.service;

import com.bank.entity.Accounts;
import com.bank.enums.AccountEnum;

public interface AccountService {
	
	public String openAccount(Accounts accounts);
	
	public Accounts accountInfo(Long accountNumber);
	
	public String closeAccount(Accounts accounts);
	
	public AccountEnum getAccountEnum();

}
