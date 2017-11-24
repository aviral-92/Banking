package com.bank.repo;

import org.springframework.data.repository.CrudRepository;

import com.bank.entity.Customer;


public interface CustomerRepository extends CrudRepository<Customer, Integer> {

	public Customer findByCustomerName(String name);
}
