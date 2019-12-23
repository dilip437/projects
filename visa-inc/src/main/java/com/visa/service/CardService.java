package com.visa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.visa.model.CreditCard;
import com.visa.repository.CardMongoRepository;

@Service
public class CardService {

	@Autowired
	private CardMongoRepository cardMongoRepository;
	
	public List<CreditCard> findAll() {
    	List<CreditCard> list = cardMongoRepository.findAll();
        return list;   
	}
	
	public String add(CreditCard entity) {
		entity = cardMongoRepository.save(entity);
    	return entity.getId();
    }
	
    public CreditCard find(String id) {
    	CreditCard entity = cardMongoRepository.findById(id).get();
		return entity;
    }

}
