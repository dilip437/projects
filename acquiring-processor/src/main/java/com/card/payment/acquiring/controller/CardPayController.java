package com.card.payment.acquiring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.card.payment.acquiring.config.Constant;
import com.card.payment.acquiring.service.CardPayService;

@RestController
@RequestMapping("/acquiring/pay")
public class CardPayController {

	@Autowired
    private CardPayService cardPayService;

	@GetMapping(path ="/{pos}")
    @ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody String pay(@PathVariable String pos,
				@RequestParam String cardNumber, @RequestParam String pin, @RequestParam Float amount) {
		System.out.println(Constant.AppName + " :: " + "CardPayController :: pay :: {pos}, {cardNumber}, {pin}, {amount}");
    	return cardPayService.pay(pos, cardNumber, pin, amount);
    }
	
//	@GetMapping(path ="/{bank}/{account}")
//    @ResponseStatus(value = HttpStatus.OK)
//	public @ResponseBody String pay(@PathVariable String bank, @PathVariable String account, 
//				@RequestParam String cardNumber, @RequestParam String pin, @RequestParam Float amount) {
//		System.out.println("CardPayController :: pay :: {bank}, {account}, {cardNumber}, {pin}, {amount}");
//    	return cardPayService.pay(bank, account, cardNumber, pin, amount);
//    }
		
	@GetMapping(path ="/{bank}/{account}/{cvv}")
    @ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody String pay(@PathVariable String bank, @PathVariable String account, @PathVariable String cvv, 
				@RequestParam String cardNumber, @RequestParam String validity, @RequestParam Float amount) {
		System.out.println(Constant.AppName + " :: " + "CardPayController :: pay :: {bank}, {account}, {cvv}, {cardNumber}, {validity}, {amount}");
    	return cardPayService.pay(bank, account, cardNumber, validity, cvv, amount);
    }
}
