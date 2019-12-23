package com.mastercard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mastercard.model.RegisterInfo;
import com.mastercard.repository.RegisterMongoRepository;

@Service
public class RegisterService {

	@Autowired
	private RegisterMongoRepository registerMongoRepository;
	
	public String add(RegisterInfo entity) {
		entity = registerMongoRepository.save(entity);
    	return entity.getId();
    }
}
