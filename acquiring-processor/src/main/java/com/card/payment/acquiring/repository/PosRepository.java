package com.card.payment.acquiring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.card.payment.acquiring.entity.PosInfo;

public interface PosRepository extends MongoRepository<PosInfo,String> {
	PosInfo findByCode(String code);
	PosInfo findByAccount(String account);
}
