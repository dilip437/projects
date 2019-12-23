package com.card.payment.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.card.payment.bank.entity.Card;
import com.card.payment.bank.repository.CardRepository;

@Service
public class CardService {

	@Autowired
	private CardRepository cardRepository;
	
	public String add(Card entity) {
		System.out.println("CardService :: add");
		entity = cardRepository.save(entity);
    	return entity.getId();
    }
	
	public String save(Card entity) {
		System.out.println("CardService :: save");
		entity = cardRepository.save(entity);
    	return entity.getId();
    }
	
    public Card find(String number) {
    	System.out.println("CardService :: find {number}");
		return cardRepository.findByNumber(number);
    }
}
