package com.eaztbytes.cards.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eaztbytes.cards.config.CardsServiceConfig;
import com.eaztbytes.cards.model.Cards;
import com.eaztbytes.cards.model.Customer;
import com.eaztbytes.cards.model.Properties;
import com.eaztbytes.cards.repository.CardsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@RestController
public class CardsController {

	@Autowired
	private CardsRepository cardsRespository;
	
	@Autowired
	private CardsServiceConfig cardsConfig;
	
	@PostMapping("/myCards")
	public List<Cards> getCardsDetails(@RequestBody Customer customer) {
		return cardsRespository.findByCustomerId(customer.getCustomerId());
	}
	
	@GetMapping("/card/properties")
	public String getPropertyDetails() throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		Properties properties = new Properties(cardsConfig.getMsg(), cardsConfig.getBuildVersion(),
				cardsConfig.getMailDetails(), cardsConfig.getActiveBranches());
		String jsonStr = ow.writeValueAsString(properties);
		return jsonStr;
	}
	
}
