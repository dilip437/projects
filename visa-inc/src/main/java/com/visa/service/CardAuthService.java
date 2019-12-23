package com.visa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.visa.remote.exception.RetryableException;
import com.visa.remote.model.BankAuthResponse;
import com.visa.remote.service.RemoteService;
import com.visa.util.CardValidator;

@Service
public class CardAuthService {

    @Autowired
	private CardValidator cardValidator;    
	
	@Autowired
	private CardSecurityInfoService cardSecurityInfoService;
	
    @Autowired
	private RemoteService remoteService;    

//	public String authorize(String cardNumber, String validity, String cvv) {
//		System.out.println("CardAuthService :: authorize :: {cardNumber}, {validity}, {cvv}");
//		String result = "AUTHORIZATION_ERROR";
//		boolean success = validate(cardNumber);
//		if(success) 
//			success = cardSecurityInfoService.verify(cardNumber, validity, cvv);
//		if(success) 
//			result = "AUTHORIZATION_SUCCESS";
//		return result;
//	}
	
	public String authorizeWithIssuer(String cardNumber, Float amount) {
		System.out.println("CardAuthService :: authorizeWithIssuer :: {cardNumber}, {amount}");
		boolean success = validate(cardNumber);
		if(!success) return "AUTHORIZATION_ERROR";
		return sendToIssuer(cardNumber, amount);
	}
	
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
	
//	private String getCvv(String cardNumber) {
//		return cardNumber.substring(cardNumber.length()-3, cardNumber.length()-1);
//	}

	public boolean validate(String cardNumber) {
		return cardValidator.validate(cardNumber);
	}
}
