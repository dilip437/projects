package com.card.payment.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.card.payment.bank.service.CardPayService;

@RestController
@RequestMapping("/bank/pay")
public class CardPayController {

	@Autowired
    private CardPayService cardPayService;

	@GetMapping(path ="/{cardNumber}/{pin}/{amount}")
    @ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody String pay(@PathVariable String cardNumber, @PathVariable String pin, @PathVariable Float amount) {
		System.out.println("CardPayController :: pay :: cardNumber, pin, amount");
		return cardPayService.pay(cardNumber, pin, amount);
    }
	
	@GetMapping(path ="/{cardNumber}")
    @ResponseStatus(value = HttpStatus.OK)
	public String pay(@PathVariable String cardNumber, 
			@RequestParam String validity, @RequestParam String cvv, @RequestParam Float amount) {
		System.out.println("CardPayController :: pay :: cardNumber, validity, cvv, amount");
//		boolean success = cardAuthService.authorize(cardNumber, validity, cvv);
		return cardPayService.pay(cardNumber, validity, cvv, amount);
	}
}
