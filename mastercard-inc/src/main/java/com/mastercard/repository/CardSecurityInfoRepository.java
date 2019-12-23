package com.mastercard.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.mastercard.model.CardSecurityInfo;

public interface CardSecurityInfoRepository extends MongoRepository<CardSecurityInfo,String> {

	@Query("{'number': ?0}")
	CardSecurityInfo findByNumber(String number);
}
