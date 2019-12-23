package com.card.payment.acquiring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.card.payment.acquiring.entity.BankInfo;

public interface BankRepository extends MongoRepository<BankInfo,String> {
	BankInfo findByAccount(String account);
}
