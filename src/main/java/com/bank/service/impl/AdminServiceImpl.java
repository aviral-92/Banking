package com.bank.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bank.entity.Accounts;
import com.bank.enums.AccountStatusEnum;
import com.bank.repo.AccountRepository;
import com.bank.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public String activeDeactivateAccount(Long AccountNumber) {

		String response = null;
		try {
			Accounts tempAccount = accountRepository.findOne(AccountNumber);
			if (!StringUtils.isEmpty(tempAccount)) {
				if (tempAccount.getAccountStatus().equals(AccountStatusEnum.PENDING_ACTIVE.name())) {
					tempAccount.setAccountStatus(AccountStatusEnum.ACTIVE.name());
					if (!StringUtils.isEmpty(accountRepository.save(tempAccount))) {
						response = AccountNumber + " successfully Activated...";
					}
				} else if (tempAccount.getAccountStatus().equals(AccountStatusEnum.PENDING_DEACTIVE.name())) {
					tempAccount.setAccountStatus(AccountStatusEnum.DEACTIVE.name());
					if (!StringUtils.isEmpty(accountRepository.save(tempAccount))) {
						response = AccountNumber + " successfully Deactivated...";
					}
				} else {
					response = "Account is already Activated or Deactivated";
				}
			} else {
				response = "Please provide valid Account Number...";
			}
		} catch (Exception e) {
			response = "Please provide valid account number...";
			System.err.println("Inside Catch block AdminServiceImpl activeDeactivateAccount()...");
		}
		return response;
	}

	@Override
	public List<Accounts> pendingActiveAccounts() {

		return accountRepository.findByAccountStatus(AccountStatusEnum.PENDING_ACTIVE.name());
	}

}
