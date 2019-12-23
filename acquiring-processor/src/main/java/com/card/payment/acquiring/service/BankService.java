package com.card.payment.acquiring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.card.payment.acquiring.entity.BankInfo;
import com.card.payment.acquiring.repository.BankRepository;

@Service
public class BankService {

	@Autowired
	private BankRepository bankRepository;
	
	public String add(BankInfo entity) {
		entity = bankRepository.save(entity);
		if(entity.getId() == null)
			return "REGISTER_BANK_ERROR";
    	return "REGISTER_BANK_SUCCESS";
    }
	
	public BankInfo find(String account) {
    	return bankRepository.findByAccount(account);
    }
}
