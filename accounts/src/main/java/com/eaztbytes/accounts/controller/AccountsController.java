package com.eaztbytes.accounts.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jca.cci.core.InteractionCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eaztbytes.accounts.model.Accounts;
import com.eaztbytes.accounts.model.Customer;
import com.eaztbytes.accounts.repository.AccountsRepository;

@RestController
public class AccountsController {

	@Autowired
	private AccountsRepository accountsRepository;
	
	@PostMapping("/myAccount")
	public Accounts getAccountDetails(@RequestBody Customer customer) {
		return accountsRepository.findByCustomerId(customer.getCustomerId());
	}
	
	@GetMapping("/accounts")
	public Iterable<Accounts> getAccounts() {
		return accountsRepository.findAll();
	}
	
}
