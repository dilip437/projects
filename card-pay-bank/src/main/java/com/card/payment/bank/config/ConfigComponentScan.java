package com.card.payment.bank.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.card.payment.bank.repository.AccountRepository;
import com.card.payment.bank.repository.CardRepository;
import com.card.payment.bank.repository.TransactionRepository;

@Configuration
@Order(1)
@EnableMongoRepositories(basePackageClasses = {
				CardRepository.class, 
				AccountRepository.class, 
				TransactionRepository.class
				})
@ComponentScan(basePackages = {"com.card.payment.bank.repository"})
public class ConfigComponentScan {

}
