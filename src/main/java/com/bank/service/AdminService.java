package com.bank.service;

import java.util.List;

import com.bank.entity.Accounts;

public interface AdminService {

	public String activeDeactivateAccount(Long AccountNumber);

	public List<Accounts> pendingActiveAccounts();
}
