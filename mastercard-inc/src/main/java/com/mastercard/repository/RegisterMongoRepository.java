package com.mastercard.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mastercard.model.RegisterInfo;

public interface RegisterMongoRepository extends MongoRepository<RegisterInfo,String> {

}
