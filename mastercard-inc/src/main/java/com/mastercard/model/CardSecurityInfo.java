package com.mastercard.model;

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
public class CardSecurityInfo {
	@Id
	private String id;
	private String number;
	private String pin;
	private String expiryDate;
	
	public CardSecurityInfo(String number, String pin) {
		super();
		this.number = number;
		this.pin = pin;
	}
	
	public CardSecurityInfo(String number, String pin, String expiryDate) {
		super();
		this.number = number;
		this.pin = pin;
		this.expiryDate = expiryDate;
	}
	
//	public String getCvv() {
//		return number.substring(number.length()-3, number.length()-1);
//	}
}
