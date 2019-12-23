package com.visa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.visa.model.CardSecurityInfo;
import com.visa.repository.CardSecurityInfoRepository;

@Service
public class CardSecurityInfoService {

	@Autowired
	private CardSecurityInfoRepository cardSecurityInfoRepository;
	
	public String add(CardSecurityInfo entity) {
		cardSecurityInfoRepository.save(entity);
    	return "success";
	}
	
	public CardSecurityInfo findByNumber(String cardNumber) {
		return cardSecurityInfoRepository.findByNumber(cardNumber);
	}
	
	public boolean verify(String cardNumber, String pin) {
		System.out.println("CardSecurityInfoService :: verify :: cardNumber, pin");
		CardSecurityInfo entity = findByNumber(cardNumber);
		if(pin.equals(entity.getPin()))
			return true;
		else
			return false;
	}
	
//	public boolean verify(String cardNumber, String validity, String cvv) {
//		System.out.println("CardSecurityInfoService :: verify :: cardNumber, validity, cvv");
//		CardSecurityInfo entity = findByNumber(cardNumber);
//		if(cvv.equals(entity.getCvv()) && validity.equals(entity.getExpiryDate()))
//			return true;
//		else
//			return false;
//	}

}
