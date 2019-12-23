package com.card.payment.bank.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.card.payment.bank.entity.Account;

public interface AccountRepository extends MongoRepository<Account,String> {

	@Query("{'number': ?0}")
	Account findByNumber(String number);
}
