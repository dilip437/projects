package com.mastercard.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mastercard.model.CreditCard;

public interface CardMongoRepository extends MongoRepository<CreditCard,String> {

}
