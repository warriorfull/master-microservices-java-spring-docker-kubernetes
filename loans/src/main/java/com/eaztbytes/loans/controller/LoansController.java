package com.eaztbytes.loans.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.eaztbytes.loans.config.LoansServiceConfig;
import com.eaztbytes.loans.model.Customer;
import com.eaztbytes.loans.model.Loans;
import com.eaztbytes.loans.model.Properties;
import com.eaztbytes.loans.repository.LoansRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@RestController
public class LoansController {

	@Autowired
	private LoansRepository loansRepository;
	
	@Autowired
	private LoansServiceConfig loansConfig;
	
	@PostMapping("/myLoans")
	public List<Loans> getLoansDetails(@RequestHeader("eaztbank-correlation-id") String correlationid, @RequestBody Customer customer)  {
		List<Loans> loans = loansRepository.findByCustomerIdOrderByStartDtDesc(customer.getCustomerId());
		if(loans != null) {
			return loans;
		} else {
			return null;
		}
	}
	
	@GetMapping("/loan/properties")
	public String getPropertiesDetails() throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		Properties properties = new Properties(loansConfig.getMsg(), loansConfig.getBuildVersion(), 
				loansConfig.getMailDetails(), loansConfig.getActiveBranches());
		String jsonStr = ow.writeValueAsString(properties);
		return jsonStr;
	}
	
}

