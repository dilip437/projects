package com.card.payment.bank.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.card.payment.bank.entity.Transaction;

public interface TransactionRepository extends MongoRepository<Transaction,String> {

	@Query("{'number': ?0}")
	Transaction findByNumber(String number);
}
