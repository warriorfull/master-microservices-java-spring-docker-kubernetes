package com.eaztbytes.cards.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eaztbytes.cards.model.Cards;
import com.eaztbytes.cards.model.Customer;
import com.eaztbytes.cards.repository.CardsRepository;

@RestController
public class CardsController {

	@Autowired
	private CardsRepository cardsRespository;
	
	@PostMapping("/myCards")
	public List<Cards> getCardsDetails(@RequestBody Customer customer) {
		return cardsRespository.findByCustomerId(customer.getCustomerId());
	}
	
}
