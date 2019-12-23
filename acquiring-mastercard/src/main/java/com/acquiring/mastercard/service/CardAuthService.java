package com.acquiring.mastercard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acquiring.mastercard.config.Constant;
import com.acquiring.mastercard.remote.exception.RetryableException;
import com.acquiring.mastercard.remote.service.MastercardRemoteService;
//import com.acquiring.mastercard.remote.service.VisaRemoteService;

@Service
public class CardAuthService {

//    @Autowired
//	private VisaRemoteService visaRemoteService;
    
    @Autowired
	private MastercardRemoteService mastercardRemoteService;
    
	public String authorizeByCardNetwork(String cardNumber, Float amount) {
		System.out.println(Constant.AppName + " :: " + "CardAuthService :: authorizeByCardNetwork :: {cardNumber}, {amount}");
		return sendToCardNetwork(cardNumber, amount);
	}
	
	private String sendToCardNetwork(String cardNumber, Float amount) {
		System.out.println(Constant.AppName + " :: " + "CardAuthService :: sendToCardNetwork :: cardNumber, amount");
		try {
//			String cardType = findCardType(cardNumber);
//			if("visa".equals(cardType)){
//				return visaRemoteService.authorizeByCardNetwork(cardNumber, amount).getResult();				
//			}else if("mastercard".equals(cardType)){
				return mastercardRemoteService.authorizeByCardNetwork(cardNumber, amount).getResult();
//			}
		} catch (RetryableException e) {
			System.out.println("RetryableException :: " + e.getMessage());
		}
		return "AUTHORIZATION_ERROR";
	}
	
//	public String findCardType(String cardNumber) {
//		if(cardNumber.startsWith("4"))
//			return "visa"; 
//		else if(cardNumber.startsWith("5"))
//			return "mastercard"; 
//		else if(cardNumber.startsWith("6"))
//			return "discover";
//		else if(cardNumber.startsWith("37") 
//			|| cardNumber.startsWith("34"))
//			return "amex";
//		else if(cardNumber.startsWith("30") 
//			|| cardNumber.startsWith("36"))
//			return "dinersclub";
//		return null;	
//	}
}
