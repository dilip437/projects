package com.card.payment.bank.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.card.payment.bank.entity.Card;

public interface CardRepository extends MongoRepository<Card,String> {

	@Query("{'number': ?0}")
	Card findByNumber(String number);
}
