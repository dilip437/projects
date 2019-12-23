package com.card.payment.acquiring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.card.payment.acquiring.entity.PosInfo;
import com.card.payment.acquiring.repository.PosRepository;

@Service
public class PosService {

	@Autowired
	private PosRepository posRepository;
	
	public String add(PosInfo entity) {
		entity = posRepository.save(entity);
		if(entity.getId() == null)
			return "REGISTER_POS_ERROR";
    	return "REGISTER_POS_SUCCESS";
    }
	
	public PosInfo find(String code) {
    	return posRepository.findByCode(code);
    }
	
	public PosInfo findByAccount(String account) {
    	return posRepository.findByAccount(account);
    }
}
