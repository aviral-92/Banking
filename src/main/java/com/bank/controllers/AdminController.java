package com.bank.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.entity.Accounts;
import com.bank.service.AccountService;
import com.bank.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController extends AbstractContoller {

	@Autowired
	private AdminService adminService;

	@RequestMapping(value = "/admin/account/activate", method = RequestMethod.POST)
	public String activteDeactivateAccount(@RequestParam Long accountNumber) {

		String response = null;
		if (!StringUtils.isEmpty(accountNumber) && accountNumber > 0) {
			response = adminService.activeDeactivateAccount(accountNumber);
		}
		return response;
	}

	@RequestMapping(value = "/admin/account/pending/activate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Accounts> ListAllPendingActiveAccount() {

		return adminService.pendingActiveAccounts();
	}

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
