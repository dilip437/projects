package com.visa.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.visa.model.RegisterInfo;

public interface RegisterMongoRepository extends MongoRepository<RegisterInfo,String> {

}
