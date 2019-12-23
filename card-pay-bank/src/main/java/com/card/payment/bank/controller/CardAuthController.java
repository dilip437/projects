package com.card.payment.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.card.payment.bank.service.CardAuthService;

@RestController
@RequestMapping("/bank/auth")
public class CardAuthController {

	@Autowired
    private CardAuthService cardAuthService;
	
	@GetMapping(path ="/authorize/{cardNumber}/{amount}")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody String authorize(@PathVariable String cardNumber, @PathVariable Float amount) {
    	if(cardAuthService.authorize(cardNumber, amount))
    		return "AUTHORIZATION_SUCCESS";
    	return "AUTHORIZATION_ERROR";
    }
	
	@GetMapping(path ="/authorize/{cardNumber}/{validity}/{amount}")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody String authorize(@PathVariable String cardNumber, @PathVariable String validity, @PathVariable Float amount) {
		if(cardAuthService.authorize(cardNumber, validity, amount))
    		return "AUTHORIZATION_SUCCESS";
    	return "AUTHORIZATION_ERROR";
	}
}
