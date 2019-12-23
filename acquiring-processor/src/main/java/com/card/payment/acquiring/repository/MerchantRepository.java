package com.card.payment.acquiring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.card.payment.acquiring.entity.MerchantInfo;

public interface MerchantRepository extends MongoRepository<MerchantInfo,String> {
	MerchantInfo findByCode(String code);
	MerchantInfo findByAccount(String account);
}
