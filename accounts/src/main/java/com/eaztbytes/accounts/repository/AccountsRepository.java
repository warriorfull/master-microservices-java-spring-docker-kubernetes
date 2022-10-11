package com.eaztbytes.accounts.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eaztbytes.accounts.model.Accounts;

@Repository
public interface AccountsRepository extends CrudRepository<Accounts, Long>{

	Accounts findByCustomerId(int customerId);
//	Optional<Accounts> findById(long customerId);
	
	@Override
	Iterable<Accounts> findAll();
	
}
