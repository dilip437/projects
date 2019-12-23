package com.card.payment.acquiring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.card.payment.acquiring.config.Constant;
import com.card.payment.acquiring.entity.PosInfo;
import com.card.payment.acquiring.remote.exception.RetryableException;
import com.card.payment.acquiring.remote.model.BankPayResponse;
import com.card.payment.acquiring.remote.service.MastercardRemoteService;
import com.card.payment.acquiring.remote.service.MerchantRemoteService;
import com.card.payment.acquiring.remote.service.VisaRemoteService;

@Service
public class CardPayService {

    @Autowired
	private VisaRemoteService visaRemoteService;
    
    @Autowired
	private MastercardRemoteService mastercardRemoteService;

    @Autowired
	private MerchantRemoteService merchantRemoteService;
    
    @Autowired
	private PosService posService;
    
    @Autowired
	private CardAuthService cardAuthService;

    public String pay(String pos, String cardNumber, String pin, Float amount) {
		System.out.println(Constant.AppName + " :: " + "CardPayService :: pay :: {pos}, {cardNumber}, {pin}, {amount}");
		
		PosInfo posInfo = posService.find(pos);
		if(posInfo == null) return "PAYMENT_ERROR";
		
		String bank = posInfo.getBank();
		if(bank == null || bank.length() <= 0) return "PAYMENT_ERROR";
		
		String account = posInfo.getAccount();
		if(account == null || account.length() <= 0) return "PAYMENT_ERROR";
		
		String result = debit(cardNumber, pin, amount);
		if("PAYMENT_SUCCESS".equals(result))
			return credit(bank, account, amount);
		return result;
	}
    
//	public String pay(String bank, String account, String cardNumber, String pin, Float amount) {
//		System.out.println(Constant.AppName + " :: " + "CardPayService :: pay :: {bank}, {account}, {cardNumber}, {pin}, {amount}");
////		boolean success = cardAuthService.authorize(cardNumber, pin);
//		String result = debit(cardNumber, pin, amount);
//		if("PAYMENT_SUCCESS".equals(result))
//			return credit(bank, account, amount);
//		return result;
//	}
	
	public String pay(String bank, String account, String cardNumber, String validity, String cvv, Float amount) {
		System.out.println(Constant.AppName + " :: " + "CardPayService :: pay :: {bank}, {account}, {cardNumber}, {validity}, {cvv}, {amount}");
//		boolean success = cardAuthService.authorize(cardNumber, validity, cvv);
		String result = debit(cardNumber, validity, cvv, amount);
		if("PAYMENT_SUCCESS".equals(result))
			return credit(bank, account, amount);
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
	
	private String credit(String bank, String account, Float amount) {
		System.out.println(Constant.AppName + " :: " + "CardPayService :: credit :: {bank}, {account}, {amount}");
		return sendToMerchant(bank, account, amount);
	}
	
	private String sendToMerchant(String bank, String account, Float amount) {
		System.out.println(Constant.AppName + " :: " + "CardPayService :: sendToMerchant :: {bank}, {account}, {amount}");
		try {
			BankPayResponse bankPayResponse = merchantRemoteService.payToMerchant(account, amount);
			return bankPayResponse.getResult();
		} catch (RetryableException e) {
			System.out.println("RetryableException :: " + e.getMessage());
		}
		return "PAYMENT_ERROR";
	}
	
	private String sendToCardNetwork(String cardNumber, String pin, Float amount) {
		System.out.println(Constant.AppName + " :: " + "CardPayService :: sendToCardNetwork :: {cardNumber}, {pin}, {amount}");
		try {
			String cardType = cardAuthService.findCardType(cardNumber);
			if("visa".equals(cardType)){
				return visaRemoteService.payByCardNetwork(cardNumber, pin, amount).getResult();				
			}else if("mastercard".equals(cardType)){
				return mastercardRemoteService.payByCardNetwork(cardNumber, pin, amount).getResult();
			}
		} catch (RetryableException e) {
			System.out.println("RetryableException :: " + e.getMessage());			
		}
		return "PAYMENT_ERROR";
	}
	
	private String sendToCardNetwork(String cardNumber, String validity, String cvv, Float amount) {
		System.out.println(Constant.AppName + " :: " + "CardPayService :: sendToCardNetwork :: {cardNumber}, {validity}, {cvv}, {amount}");
		try {
			String cardType = cardAuthService.findCardType(cardNumber);
			if("visa".equals(cardType)){
				return visaRemoteService.payByCardNetwork(cardNumber, validity, cvv, amount).getResult();				
			}else if("mastercard".equals(cardType)){
				return mastercardRemoteService.payByCardNetwork(cardNumber, validity, cvv, amount).getResult();
			}
		} catch (RetryableException e) {
			System.out.println("RetryableException :: " + e.getMessage());
			
		}
		return "PAYMENT_ERROR";
	}
}
