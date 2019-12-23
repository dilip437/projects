package com.visa.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.visa.model.CardSecurityInfo;

public interface CardSecurityInfoRepository extends MongoRepository<CardSecurityInfo,String> {

	@Query("{'number': ?0}")
	CardSecurityInfo findByNumber(String number);
}
