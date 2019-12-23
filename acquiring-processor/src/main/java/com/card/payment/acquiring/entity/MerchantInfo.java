package com.card.payment.acquiring.entity;

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
public class MerchantInfo {
	@Id
	private String id;
	private String code;
	private String name;
	private String bank;
	private String account;
	
	public MerchantInfo(String code, String name, String bank, String account) {
		super();
		this.code = code;
		this.name = name;
		this.bank = bank;
		this.account = account;
	}
	
	public MerchantInfo(String code, String bank, String account) {
		super();
		this.code = code;
		this.bank = bank;
		this.account = account;
	}
}
