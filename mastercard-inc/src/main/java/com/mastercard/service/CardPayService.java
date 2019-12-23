package com.mastercard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mastercard.remote.exception.RetryableException;
import com.mastercard.remote.model.BankPayResponse;
import com.mastercard.remote.service.RemoteService;

@Service
public class CardPayService {

//	@Autowired
//	private CardAuthService cardAuthService;
	
    @Autowired
	private RemoteService remoteService;    
    
	public String pay(String cardNumber, String pin, Float amount) {
		System.out.println("CardPayService :: pay");
//		boolean success = cardAuthService.authorize(cardNumber, pin);
		return sendToIssuer(cardNumber, pin, amount);
	}
	
	public String pay(String cardNumber, String validity, String cvv, Float amount) {
		System.out.println("CardPayService :: pay");
//		boolean success = cardAuthService.authorize(cardNumber, validity, cvv);
		return sendToIssuer(cardNumber, validity, cvv, amount);
	}
	
	private String sendToIssuer(String cardNumber, String pin, Float amount) {
		System.out.println("CardPayService :: sendToIssuer :: cardNumber, pin, amount");
		try {
			BankPayResponse bankPayResponse = remoteService.payByIssuer(cardNumber, pin, amount);
			return bankPayResponse.getResult();
		} catch (RetryableException e) {
			System.out.println("RetryableException :: " + e.getMessage());
			return "PAYMENT_ERROR";
		}
	}
	
	private String sendToIssuer(String cardNumber, String validity, String cvv, Float amount) {
		System.out.println("CardPayService :: sendToIssuer :: cardNumber, validity, cvv, amount");
		try {
			BankPayResponse bankPayResponse = remoteService.payByIssuer(cardNumber, validity, cvv, amount);
			return bankPayResponse.getResult();
		} catch (RetryableException e) {
			System.out.println("RetryableException :: " + e.getMessage());
			return "PAYMENT_ERROR";
		}
	}
}
