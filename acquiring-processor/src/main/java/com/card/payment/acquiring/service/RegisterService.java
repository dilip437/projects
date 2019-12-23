package com.card.payment.acquiring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.card.payment.acquiring.entity.RegisterInfo;
import com.card.payment.acquiring.repository.RegisterMongoRepository;

@Service
public class RegisterService {

	@Autowired
	private RegisterMongoRepository registerMongoRepository;
	
	public String add(RegisterInfo entity) {
		registerMongoRepository.save(entity);
    	return "REGISTER_SUCCESS";
    }
}
