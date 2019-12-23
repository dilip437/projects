package com.visa.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.visa.repository.CardMongoRepository;
import com.visa.repository.CardSecurityInfoRepository;
import com.visa.repository.RegisterMongoRepository;

@Configuration
@Order(1)
@EnableMongoRepositories(basePackageClasses = {
							CardMongoRepository.class, 
							RegisterMongoRepository.class, 
							CardSecurityInfoRepository.class
						})
@ComponentScan(basePackages = {"com.visa"})
public class ConfigComponentScan {

}
