package com.card.payment.bank.model;

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
public class CardPaymentInfo {
	private String number;
	private String pin;
	private String cvv;
	private String expiryDate;
	private Float amount;
}
