package com.card.payment.acquiring.remote.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BankPayRequest {
	private String account;
	private String cardNumber;
	private String pin;
	private Float amount;
	
	private String validity; 
	private String cvv;
	
	public BankPayRequest(String cardNumber, String pin, Float amount) {
		super();
		this.cardNumber = cardNumber;
		this.pin = pin;
		this.amount = amount;
	}

	public BankPayRequest(String account, Float amount) {
		super();
		this.account = account;
		this.amount = amount;
	}
	
	public BankPayRequest(String cardNumber, String validity, String cvv, Float amount) {
		super();
		this.cardNumber = cardNumber;
		this.validity = validity;
		this.cvv = cvv;
		this.amount = amount;
	}
}
