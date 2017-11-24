package com.bank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "CUSTOMER")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Customer {

	@Id
	@Column(name = "CUSTOMER_ADHAAR_NUMBER")
	private Long customerAdhaarNumber;

	@Column(name = "CUSTOMER_NAME")
	private String customerName;

	@Column(name = "CUSTOMER_ADDRESS")
	private String customerAddress;

	@Column(name = "CUSTOMER_ID_PROOF")
	private String customerIdProof;

	@Column(name = "CUSTOMER_STATUS")
	private String status;

	public Long getCustomerAdhaarNumber() {
		return customerAdhaarNumber;
	}

	public void setCustomerAdhaarNumber(Long customerAdhaarNumber) {
		this.customerAdhaarNumber = customerAdhaarNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerIdProof() {
		return customerIdProof;
	}

	public void setCustomerIdProof(String customerIdProof) {
		this.customerIdProof = customerIdProof;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
