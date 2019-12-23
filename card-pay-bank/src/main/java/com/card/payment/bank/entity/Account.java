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
public class Account {
	@Id
	private String id;
	private String number;
	private String name;
	private String bank;
	private String ifsc;
	private Float balance;
	
	public Account(String number, String name, String bank, String ifsc, Float balance) {
		super();
		this.number = number;
		this.name = name;
		this.bank = bank;
		this.ifsc = ifsc;
		this.balance = balance;
	}
}
