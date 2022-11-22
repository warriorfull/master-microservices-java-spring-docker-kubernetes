package com.eaztbytes.accounts.controller;

import com.eaztbytes.accounts.model.Properties;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.eaztbytes.accounts.config.AccountsServiceConfig;
import com.eaztbytes.accounts.model.Accounts;
import com.eaztbytes.accounts.model.Cards;
import com.eaztbytes.accounts.model.Customer;
import com.eaztbytes.accounts.model.CustomerDetails;
import com.eaztbytes.accounts.model.Loans;
import com.eaztbytes.accounts.repository.AccountsRepository;
import com.eaztbytes.accounts.service.client.CardsFeignClient;
import com.eaztbytes.accounts.service.client.LoansFeignClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.micrometer.core.annotation.Timed;

@RestController
public class AccountsController {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountsController.class);

	@Autowired
	private AccountsRepository accountsRepository;
	
	@Autowired
	private AccountsServiceConfig accountsConfig;

	@Autowired
	private LoansFeignClient loansFeignClient;
	
	@Autowired
	private CardsFeignClient cardsFeignClient;
	
	@PostMapping("/myAccount")
	@Timed(value = "getAccountDetails.time", description = "Time taken to return Account Details")
	public Accounts getAccountDetails(@RequestBody Customer customer) {
		return accountsRepository.findByCustomerId(customer.getCustomerId());
	}
	
	@GetMapping("/accounts")
	public Iterable<Accounts> getAccounts() {
		return accountsRepository.findAll();
	}
	
	@GetMapping("/account/properties")
	public String getPropertyDetails() throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		Properties properties = new Properties(accountsConfig.getMsg(), accountsConfig.getBuildVersion(),
				accountsConfig.getMailDetails(), accountsConfig.getActiveBranches());
		String jsonStr = ow.writeValueAsString(properties);
		return jsonStr;
	}
	
	@PostMapping("/myCustomerDetails")
	//	@Retry(name = "retryForCustormerDetails")
	@Retry(name = "retryForCustormerDetails", fallbackMethod = "myCustomerDetailsFallBack")
//	@CircuitBreaker(name = "detailsForCustomerSupportApp", fallbackMethod = "myCustomerDetailsFallBack")
	@CircuitBreaker(name = "detailsForCustomerSupportApp")
	public CustomerDetails myCustomerDetails(@RequestHeader("eaztbank-correlation-id") String correlationid, @RequestBody Customer customer) {
		logger.info("myCustomerDetails() method started !!!!!");
		Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
		List<Loans> loans = loansFeignClient.getLoansDetails(correlationid, customer);
		List<Cards> cards = cardsFeignClient.getCardDetails(correlationid, customer);
		
		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setAccounts(accounts);
		customerDetails.setLoans(loans);
		customerDetails.setCards(cards);
		logger.info("myCustomerDetails() method ended");
		
		return customerDetails;
		
	}
	
	private CustomerDetails myCustomerDetailsFallBack(@RequestHeader("eaztbank-correlation-id") String correlationid, Customer customer, Throwable t) {
		logger.info("myCustomerDetailsFallBack() method started !!!-!!!");
		Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
		List<Loans> loans = loansFeignClient.getLoansDetails(correlationid, customer);
				
		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setAccounts(accounts);
		customerDetails.setLoans(loans);
		
		return customerDetails;
	}
	
	@GetMapping("/sayHello")
//	@RateLimiter(name = "sayHello", fallbackMethod = "sayHelloFallback")
	public String sayHello() {
		return "Hello, Welcome to Eazybank";
	}
	
	private String sayHelloFallback(Throwable t) {
		return "Hi, Welcome to Eaztbank";
	}
}
