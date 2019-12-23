package com.card.pos.remote.model;

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

	public BankPayRequest(Float amount) {
		super();
		this.amount = amount;
	}
}
