package com.eaztbytes.accounts.service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eaztbytes.accounts.model.Cards;
import com.eaztbytes.accounts.model.Customer;

@FeignClient("cards")
public interface CardsFeignClient {

	@RequestMapping(method = RequestMethod.POST, value = "myCards", consumes = "application/json")
	List<Cards> getCardDetails(@RequestHeader("eaztbank-correlatiuon-id") String correlationid, @RequestBody Customer customer);
}
