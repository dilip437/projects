package com.acquiring.visa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.acquiring.visa.config.Constant;
import com.acquiring.visa.service.CardPayService;

@RestController
@RequestMapping("/acquiring/visa/pay")
public class CardPayController {

	@Autowired
    private CardPayService cardPayService;

	@GetMapping(path ="/{cardNumber}")
    @ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody String pay(@PathVariable String cardNumber, @RequestParam String pin, @RequestParam Float amount) {
		System.out.println(Constant.AppName + " :: " + "CardPayController :: pay :: {cardNumber}, {pin}, {amount}");
		return cardPayService.pay(cardNumber, pin, amount);
    }
	
//	@GetMapping(path ="/{bank}/{account}")
//    @ResponseStatus(value = HttpStatus.OK)
//	public @ResponseBody String pay(@PathVariable String bank, @PathVariable String account, 
//				@RequestParam String cardNumber, @RequestParam String pin, @RequestParam Float amount) {
//		System.out.println("CardPayController :: pay :: {bank}, {account}, {cardNumber}, {pin}, {amount}");
//    	return cardPayService.pay(bank, account, cardNumber, pin, amount);
//    }
		
	@GetMapping(path ="/{cardNumber}/{cvv}")
    @ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody String pay(@PathVariable String cardNumber, @PathVariable String cvv, 
			@RequestParam String validity, @RequestParam Float amount) {
		System.out.println(Constant.AppName + " :: " + "CardPayController :: pay :: {cardNumber}, {cvv}, {validity}, {amount}");
    	return cardPayService.pay(cardNumber, validity, cvv, amount);
    }
}
