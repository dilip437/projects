package com.acquiring.mastercard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acquiring.mastercard.config.Constant;
//import com.acquiring.mastercard.entity.PosInfo;
import com.acquiring.mastercard.remote.exception.RetryableException;
import com.acquiring.mastercard.remote.service.MastercardRemoteService;
//import com.acquiring.mastercard.remote.service.MerchantRemoteService;
//import com.acquiring.mastercard.remote.service.VisaRemoteService;

@Service
public class CardPayService {

//    @Autowired
//	private VisaRemoteService visaRemoteService;
    
    @Autowired
	private MastercardRemoteService mastercardRemoteService;

//    @Autowired
//	private MerchantRemoteService merchantRemoteService;
        
//    @Autowired
//	private CardAuthService cardAuthService;

    public String pay(String cardNumber, String pin, Float amount) {
		System.out.println(Constant.AppName + " :: " + "CardPayService :: pay :: {pos}, {cardNumber}, {pin}, {amount}");
		return debit(cardNumber, pin, amount);
	}
    
//		System.out.println("CardPayService :: pay :: {pos}, {cardNumber}, {pin}, {amount}");
//		return debit(cardNumber, pin, amount);
//    public String pay(String pos, String cardNumber, String pin, Float amount) {
//	}
    
//	public String pay(String bank, String account, String cardNumber, String pin, Float amount) {
//		System.out.println("CardPayService :: pay :: {bank}, {account}, {cardNumber}, {pin}, {amount}");
//		String result = debit(cardNumber, pin, amount);
//		if("PAYMENT_SUCCESS".equals(result))
//			return credit(bank, account, amount);
//		return result;
//	}
	
//	public String pay(String bank, String account, String cardNumber, String validity, String cvv, Float amount) {
//		System.out.println("CardPayService :: pay :: {bank}, {account}, {cardNumber}, {validity}, {cvv}, {amount}");
//		String result = debit(cardNumber, validity, cvv, amount);
////		if("PAYMENT_SUCCESS".equals(result))
////			return credit(bank, account, amount);
//		return result;
//	}
	
	public String pay(String cardNumber, String validity, String cvv, Float amount) {
		System.out.println(Constant.AppName + " :: " + "CardPayService :: pay :: {bank}, {account}, {cardNumber}, {validity}, {cvv}, {amount}");
		String result = debit(cardNumber, validity, cvv, amount);
//		if("PAYMENT_SUCCESS".equals(result))
//			return credit(bank, account, amount);
		return result;
	}
	
	private String debit(String cardNumber, String pin, Float amount) {
		System.out.println(Constant.AppName + " :: " + "CardPayService :: debit :: {cardNumber}, {pin}, {amount}");
		return sendToCardNetwork(cardNumber, pin, amount);
	}
	
	private String debit(String cardNumber, String validity, String cvv, Float amount) {
		System.out.println(Constant.AppName + " :: " + "CardPayService :: debit :: {cardNumber}, {validity}, {cvv}, {amount}");
		return sendToCardNetwork(cardNumber, validity, cvv, amount);
	}
	
//	private String credit(String bank, String account, Float amount) {
//		System.out.println("CardPayService :: credit :: {bank}, {account}, {amount}");
//		return sendToMerchant(bank, account, amount);
//	}
	
//	private String sendToMerchant(String bank, String account, Float amount) {
//		System.out.println("CardPayService :: sendToMerchant :: {bank}, {account}, {amount}");
//		try {
//			BankPayResponse bankPayResponse = merchantRemoteService.payToMerchant(account, amount);
//			return bankPayResponse.getResult();
//		} catch (RetryableException e) {
//			System.out.println("RetryableException :: " + e.getMessage());
//			return "PAYMENT_ERROR";
//		}
//	}
	
	private String sendToCardNetwork(String cardNumber, String pin, Float amount) {
		System.out.println(Constant.AppName + " :: " + "CardPayService :: sendToCardNetwork :: {cardNumber}, {pin}, {amount}");
		try {
//			String cardType = cardAuthService.findCardType(cardNumber);
//			if("visa".equals(cardType)){
//				return visaRemoteService.payByCardNetwork(cardNumber, pin, amount).getResult();				
//			}else if("mastercard".equals(cardType)){
				return mastercardRemoteService.payByCardNetwork(cardNumber, pin, amount).getResult();
//			}
		} catch (RetryableException e) {
			System.out.println("RetryableException :: " + e.getMessage());			
		}
		return "PAYMENT_ERROR";
	}
	
	private String sendToCardNetwork(String cardNumber, String validity, String cvv, Float amount) {
		System.out.println(Constant.AppName + " :: " + "CardPayService :: sendToCardNetwork :: {cardNumber}, {validity}, {cvv}, {amount}");
		try {
//			String cardType = cardAuthService.findCardType(cardNumber);
//			if("visa".equals(cardType)){
//				return visaRemoteService.payByCardNetwork(cardNumber, validity, cvv, amount).getResult();
//			}else if("mastercard".equals(cardType)){
				return mastercardRemoteService.payByCardNetwork(cardNumber, validity, cvv, amount).getResult();
//			}
		} catch (RetryableException e) {
			System.out.println("RetryableException :: " + e.getMessage());
			
		}
		return "PAYMENT_ERROR";
	}
}
