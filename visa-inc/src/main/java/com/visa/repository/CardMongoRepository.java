package com.visa.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.visa.model.CreditCard;

public interface CardMongoRepository extends MongoRepository<CreditCard,String> {

}
