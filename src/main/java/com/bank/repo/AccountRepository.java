package com.bank.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bank.constants.QueryConstant;
import com.bank.entity.Accounts;

public interface AccountRepository extends CrudRepository<Accounts, Long> {

	public List<Accounts> findByAccountAdhaarNumber(Long adhaarNumber);

	//@Query(nativeQuery = true, value = QueryConstant.SELECT_ALL_PENDING_ACTIVE_ACCOUNTS)
	public List<Accounts> findByAccountStatus(String status);
}
