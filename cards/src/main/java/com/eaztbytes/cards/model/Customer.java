package com.eaztbytes.cards.model;

public class Customer {

	private int customerId;


	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	public Customer() {
		
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + "]";
	}
}
