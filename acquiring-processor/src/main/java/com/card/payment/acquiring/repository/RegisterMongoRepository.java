package com.card.payment.acquiring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.card.payment.acquiring.entity.RegisterInfo;

public interface RegisterMongoRepository extends MongoRepository<RegisterInfo,String> {

}
