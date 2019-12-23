package com.card.pos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.card.pos.remote.exception.RetryableException;
import com.card.pos.remote.model.BankPayResponse;
import com.card.pos.remote.service.AcquiringRemoteService;

@Service
public class CardPayService {
	
//	private static final String pos = "P125";
//	private static final String bank = "1755";
//	private static final String account = "45678901";

	@Value("${pos.number}")
	private String pos;
	
	@Autowired
	private CardValidator cardValidator;

	@Autowired
	private AcquiringRemoteService acquiringRemoteService;
	
	public String validate(String cardNumber) {
		if(cardValidator.validate(cardNumber))
			return "CARD_VALID";
		return "CARD_INVALID";
	}
	
	public String pay(String cardNumber, String pin, Float amount) {
		System.out.println("CardPayService :: pay :: {cardNumber}, {pin}, {amount}");
		if("CARD_INVALID".equals(validate(cardNumber)))
			return "PAYMENT_ERROR";
		return sendToAcquiringProcessor(cardNumber, pin, amount);
	}
	
	private String sendToAcquiringProcessor(String cardNumber, String pin, Float amount) {
		System.out.println("CardPayService :: sendToAcquiringProcessor :: {cardNumber}, {pin}, {amount}");
		try {
			BankPayResponse bankPayResponse = acquiringRemoteService.payToMerchant(pos, cardNumber, pin, amount);
			return bankPayResponse.getResult();
		} catch (RetryableException e) {
			System.out.println("RetryableException :: " + e.getMessage());
			return "PAYMENT_ERROR";
		}
	}
}
