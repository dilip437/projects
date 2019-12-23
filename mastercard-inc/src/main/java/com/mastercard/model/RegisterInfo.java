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
public class RegisterInfo {
	@Id
	private String id;
	private String number;
	private String bank;
	private String bankAccount;
	
	public RegisterInfo(String number, String bank, String bankAccount) {
		super();
		this.number = number;
		this.bank = bank;
		this.bankAccount = bankAccount;
	}
}
