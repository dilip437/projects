package com.mastercard.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mastercard.repository.CardMongoRepository;
import com.mastercard.repository.CardSecurityInfoRepository;
import com.mastercard.repository.RegisterMongoRepository;

@Configuration
@Order(1)
@EnableMongoRepositories(basePackageClasses = {
							CardMongoRepository.class, 
							RegisterMongoRepository.class, 
							CardSecurityInfoRepository.class
						})
@ComponentScan(basePackages = {"com.mastercard"})
public class ConfigComponentScan {

}
