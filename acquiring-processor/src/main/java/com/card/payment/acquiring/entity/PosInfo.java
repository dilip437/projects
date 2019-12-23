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
public class PosInfo {
	@Id
	private String id;
	private String code;
	private String merchant;
	private String bank;
	private String account;
	
	public PosInfo(String code, String merchant, String bank, String account) {
		super();
		this.code = code;
		this.merchant = merchant;
		this.bank = bank;
		this.account = account;
	}

	public PosInfo(String code, String account) {
		super();
		this.code = code;
		this.account = account;
	}
	
}
