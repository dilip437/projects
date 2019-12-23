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
public class Transaction {
	@Id
	private String id;
	private String number;
	private String account;
	private String date;
	private Boolean inOut;
	private Float amount;
	private Float balance;
	
	public Transaction(String number, String account, String date, Boolean inOut, Float amount, Float balance) {
		super();
		this.number = number;
		this.account = account;
		this.date = date;
		this.inOut = inOut;
		this.amount = amount;
		this.balance = balance;
	}
}
