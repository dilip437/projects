package com.visa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.visa.remote.BankAuthResponse;
import com.visa.remote.RemoteService;
import com.visa.remote.RetryableException;
import com.visa.util.CardValidator;

@Service
public class CardAuthService {

    @Autowired
	private CardValidator cardValidator;    
	
	@Autowired
	private CardSecurityInfoService cardSecurityInfoService;
	
    @Autowired
	private RemoteService remoteService;    

	public String authorize(String cardNumber, String validity, String cvv) {
		System.out.println("CardAuthService :: authorize :: {cardNumber}, {validity}, {cvv}");
		String result = "AUTHORIZATION_ERROR";
		boolean success = validate(cardNumber);
		if(success) 
			success = cardSecurityInfoService.verify(cardNumber, validity, cvv);
		if(success) 
			result = "AUTHORIZATION_SUCCESS";
		return result;
	}
	
//	public String authorizeWithIssuer(String cardNumber, String validity, Float amount) {
//		System.out.println("CardAuthService :: authorizeWithIssuer :: {cardNumber}, {validity}, {amount}");
//		String result = "AUTHORIZATION_ERROR";
//		boolean success = validate(cardNumber);
//		if(success) 
//			success = cardSecurityInfoService.verify(cardNumber, validity, getCvv(cardNumber));
//		if(success) 
//			result = sendToIssuer(cardNumber, validity, amount);
//		return result;
//	}
	
	public String authorizeWithIssuer(String cardNumber, Float amount) {
		System.out.println("CardAuthService :: authorizeWithIssuer :: {cardNumber}, {amount}");
		boolean success = validate(cardNumber);
		if(!success) return "AUTHORIZATION_ERROR";
		return sendToIssuer(cardNumber, amount);
	}
	
//	public String authorize(String cardNumber, String pin) {
//		System.out.println("CardAuthService :: authorize :: {cardNumber}, {pin}");
//		String result = "AUTHORIZATION_ERROR";
//		boolean success = validate(cardNumber);
//		if(success) 
//			success = cardSecurityInfoService.verify(cardNumber, pin);
//		if(success) 
//			result = "AUTHORIZATION_SUCCESS";
//		return result;
//	}
	
//	private String sendToIssuer(String cardNumber, String validity, Float amount) {
//		System.out.println("CardAuthService :: sendToIssuer :: cardNumber, validity, amount");
//		String result = "AUTHORIZATION_NOT_SUPPORT";
//		return result;
//	}
	
	private String sendToIssuer(String cardNumber, Float amount) {
		System.out.println("CardAuthService :: sendToIssuer :: cardNumber, amount");
		String result = "AUTHORIZATION_ERROR";
		try {
			BankAuthResponse bankAuthResponse = remoteService.authorizeByIssuer(cardNumber, amount);
			result = bankAuthResponse.getResult();
		} catch (RetryableException e) {
			System.out.println("RetryableException :: " + e.getMessage());
		}
		return result;
	}
	
	private String getCvv(String cardNumber) {
		return cardNumber.substring(cardNumber.length()-3, cardNumber.length()-1);
	}

	public boolean validate(String cardNumber) {
		return cardValidator.validate(cardNumber);
	}
}
