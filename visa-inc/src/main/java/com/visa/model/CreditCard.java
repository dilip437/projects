package com.visa.model;

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
public class CreditCard {
	@Id
	private String id;
	private String name;
	private String number;
	private String expiryDate;
	
	public CreditCard(String name, String number, String expiryDate) {
		super();
		this.name = name;
		this.number = number;
		this.expiryDate = expiryDate;
	}

	public CreditCard(String name) {
		this.name = name;
	}
}
