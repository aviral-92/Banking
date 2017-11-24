package com.bank.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT_TRANSACTION")
@SequenceGenerator(name = "ACCOUNT_SEQ", initialValue = 10001)
public class AccountTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOUNT_SEQ")
	@Column(name = "TRANSACTION_REFRENCE_NUMBER")
	private Integer transactionRefrenceNumber;

	@Column(name = "CUSTOMER_ACCOUNT_NUMBER", columnDefinition = "Transaction Account Number")
	private String customerAccountNumber;

	@Column(name = "CREDIT_AMOUNT")
	private String creditAmount;

	@Column(name = "DebIT_AMOUNT")
	private String debitAmount;

	@Column(name = "TRANSACTION_STATUS", nullable = false)
	private String transactionStatus;

	@Column(name = "TRANSACTION_COMMENTS", nullable = false)
	private String transactionComments;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CUSTOMER_ACCOUNT_NUMBER", referencedColumnName = "ACCOUNT_NUMBER", insertable = false, updatable = false)
	private Accounts accounts;

	public Integer getTransactionRefrenceNumber() {
		return transactionRefrenceNumber;
	}

	public void setTransactionRefrenceNumber(Integer transactionRefrenceNumber) {
		this.transactionRefrenceNumber = transactionRefrenceNumber;
	}

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

	public Accounts getAccounts() {
		return accounts;
	}

	public void setAccounts(Accounts accounts) {
		this.accounts = accounts;
	}

	public String getTransactionComments() {
		return transactionComments;
	}

	public void setTransactionComments(String transactionComments) {
		this.transactionComments = transactionComments;
	}

}
