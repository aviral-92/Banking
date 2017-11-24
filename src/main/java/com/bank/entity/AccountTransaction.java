package com.bank.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT_TRANSACTION")
public class AccountTransaction {

	@Id
	@Column(name = "CUSTOMER_ACCOUNT_NUMBER")
	private String customerAccountNumber;

	@Column(name = "CREDIT_AMOUNT")
	private String creditAmount;

	@Column(name = "DebIT_AMOUNT")
	private String debitAmount;

	@Column(name = "TRANSACTION_STATUS")
	private String transactionStatus;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CUSTOMER_ACCOUNT_NUMBER", referencedColumnName = "ACCOUNT_NUMBER", insertable = false, updatable = false)
	private Customer customer;

	public String getCustomerAccountNumber() {
		return customerAccountNumber;
	}

	public void setCustomerAccountNumber(String customerAccountNumber) {
		this.customerAccountNumber = customerAccountNumber;
	}

	public String getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(String creditAmount) {
		this.creditAmount = creditAmount;
	}

	public String getDebitAmount() {
		return debitAmount;
	}

	public void setDebitAmount(String debitAmount) {
		this.debitAmount = debitAmount;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
