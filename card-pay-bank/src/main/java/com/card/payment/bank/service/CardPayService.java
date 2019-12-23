package com.card.payment.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.card.payment.bank.entity.Card;

@Service
public class CardPayService {

	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private CardService cardService;
	
	@Autowired
	private CardAuthService cardAuthService;

	public String pay(String cardNumber, String pin, Float amount) {
		System.out.println("CardPayService :: pay :: {cardNumber}, {pin}, {amount}");
		Card card = cardService.find(cardNumber);
		if(card == null) return "PAYMENT_ERROR";
		if(!pin.equals(card.getPin())) return "PAYMENT_ERROR";
		if(!cardAuthService.authorize(card, cardNumber, amount)) return "PAYMENT_ERROR";
		String accountNumber = card.getAccount();
		if(accountNumber == null || accountNumber.length() <= 0) return "PAYMENT_ERROR";
		return transactionService.pay(accountNumber, amount);
	}
	
	public String pay(String cardNumber, String validity, String cvv, Float amount) {
		System.out.println("CardPayService :: pay :: {cardNumber}, {validity}, {cvv}, {amount}");
		Card card = cardService.find(cardNumber);
		if(card == null) return "PAYMENT_ERROR";
		if(!cvv.equals(card.getCvv())) return "PAYMENT_ERROR";
		if(!cardAuthService.authorize(card, cardNumber, validity, amount)) return "PAYMENT_ERROR";
		String accountNumber = card.getAccount();
		if(accountNumber == null || accountNumber.length() <= 0) return "PAYMENT_ERROR";
		return transactionService.pay(accountNumber, amount);
	}
}
