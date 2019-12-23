package com.card.payment.acquiring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

//import com.card.payment.acquiring.repository.CardMongoRepository;
//import com.card.payment.acquiring.repository.CardSecurityInfoRepository;
import com.card.payment.acquiring.repository.RegisterMongoRepository;

@Configuration
@Order(1)
@EnableMongoRepositories(basePackageClasses = {RegisterMongoRepository.class})
@ComponentScan(basePackages = {"com.card.payment.acquiring"})
public class ConfigComponentScan {

}
