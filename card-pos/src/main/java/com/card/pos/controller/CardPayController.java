package com.card.pos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.card.pos.service.CardPayService;

@RestController
@RequestMapping("/pos")
public class CardPayController {

	@Autowired
    private CardPayService cardPayService;

	@GetMapping(path ="/pay")
    @ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody String pay(@RequestParam String cardNumber, @RequestParam String pin, @RequestParam Float amount) {
		System.out.println("CardPayController :: pay :: cardNumber, pin, amount");
    	return cardPayService.pay(cardNumber, pin, amount);
    }
	
	@GetMapping(path ="/validate/{cardNumber}")
    @ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody String validate(@PathVariable String cardNumber) {
		System.out.println("CardPayController :: validate :: {cardNumber}");
    	return cardPayService.validate(cardNumber);
    }
}
