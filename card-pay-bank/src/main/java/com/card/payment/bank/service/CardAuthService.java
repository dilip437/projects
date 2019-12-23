package com.card.payment.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.card.payment.bank.entity.Account;
import com.card.payment.bank.entity.Card;
import com.card.payment.bank.util.DateUtil;

@Service
public class CardAuthService {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private CardService cardService;
	
	@Autowired
	private DateUtil dateUtil;

	public boolean authorize(String cardNumber, Float amount) {
		System.out.println("CardAuthService :: authorize :: {cardNumber}, {amount}");
		return authorize(cardNumber, null, amount);
	}
	
	public boolean authorize(Card card, String cardNumber, Float amount) {
		System.out.println("CardAuthService :: authorize :: {card}, {cardNumber}, {amount}");
		return authorize(card, cardNumber, null, amount);
	}

	public boolean authorize(String cardNumber, String validity, Float amount) {
		System.out.println("CardAuthService :: authorize :: {cardNumber}, {validity}, {amount}");
		Card card = cardService.find(cardNumber);
		if(card == null) return false;
		return authorize(card, cardNumber, validity, amount);
	}

	public boolean authorize(Card card, String cardNumber, String validity, Float amount) {
		System.out.println("CardAuthService :: authorize :: {card}, {cardNumber}, {validity}, {amount}");
				
		String accountNumber = card.getAccount();
		if(accountNumber == null || accountNumber.length() <= 0) return false;
		
		if(validity != null) {
			if(!validity.equals(card.getExpiryDate())) return false;	
		}

		if(dateUtil.cardValidityToDate(card.getExpiryDate()).compareTo(new java.util.Date()) < 0) return false;
		
		Account account = accountService.find(accountNumber);
		if(account == null) return false;
		
		Float balance = account.getBalance();
		if(balance < amount) return false;
		
		return true;
	}
}
