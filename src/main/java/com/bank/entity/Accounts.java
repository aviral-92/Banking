package com.bank.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNTS")
@SequenceGenerator(name = "ACCOUNT_SEQ", initialValue = 1000)
public class Accounts {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOUNT_SEQ")
	@Column(name = "ACCOUNT_NUMBER")
	private Long AccountNumber;

	@Column(name = "ACCOUNT_ADHAAR_NUMBER", nullable = false)
	private Long accountAdhaarNumber;

	@Column(name = "ACCOUNT_BALANCE")
	private String accountBalance;

	@Column(name = "ACCOUNT_TYPE", nullable = false)
	private String accountType;

	@Column(name = "ACCOUNT_INTEREST_RATE")
	private String accountInterestRate;

	@Column(name = "ACCOUNT_STATUS")
	private String accountStatus;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCOUNT_ADHAAR_NUMBER", referencedColumnName = "CUSTOMER_ADHAAR_NUMBER", insertable = false, updatable = false)
	private Customer customer;

	public Long getAccountNumber() {
		return AccountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		AccountNumber = accountNumber;
	}

	public Long getAccountAdhaarNumber() {
		return accountAdhaarNumber;
	}

	public void setAccountAdhaarNumber(Long accountAdhaarNumber) {
		this.accountAdhaarNumber = accountAdhaarNumber;
	}

	public String getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(String accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountInterestRate() {
		return accountInterestRate;
	}

	public void setAccountInterestRate(String accountInterestRate) {
		this.accountInterestRate = accountInterestRate;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
