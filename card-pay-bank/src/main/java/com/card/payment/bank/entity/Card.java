package com.card.payment.bank.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Card {
	@Id
	private String id;
	private String name;
	private String number;
	private String expiryDate;
	private String pin;
	private String cvv;
	private String account;
	
	public Card(String name, String number, String expiryDate, String pin, String cvv, String account) {
		super();
		this.name = name;
		this.number = number;
		this.expiryDate = expiryDate;
		this.pin = pin;
		this.cvv = cvv;
		this.account = account;
	}
}
