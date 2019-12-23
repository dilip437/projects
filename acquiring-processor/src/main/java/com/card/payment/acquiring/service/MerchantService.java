package com.card.payment.acquiring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.card.payment.acquiring.entity.MerchantInfo;
import com.card.payment.acquiring.repository.MerchantRepository;

@Service
public class MerchantService {

	@Autowired
	private MerchantRepository merchantRepository;
	
	public String add(MerchantInfo entity) {
		entity = merchantRepository.save(entity);
		if(entity.getId() == null)
			return "REGISTER_MERCHANT_ERROR";
    	return "REGISTER_MERCHANT_SUCCESS";
    }
	
	public MerchantInfo find(String code) {
    	return merchantRepository.findByCode(code);
    }
	
	public MerchantInfo findByAccount(String account) {
    	return merchantRepository.findByAccount(account);
    }
}
