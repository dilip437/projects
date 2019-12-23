package com.mastercard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mastercard.model.CreditCard;
import com.mastercard.service.CardAuthService;
import com.mastercard.service.CardService;

@RestController
@RequestMapping("/mastercard/cc")
public class CardController {

    @Autowired
    private CardService cardService;
	
    @Autowired
    private CardAuthService cardAuthService;
    
    @GetMapping(path ="/")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody List<CreditCard> all() {
    	return cardService.findAll();
    }
    
    @GetMapping(path ="/add")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody String add(@RequestParam String name, @RequestParam String number, @RequestParam String expiryDate) {
    	return cardService.add(new CreditCard(name, number, expiryDate));
    }
    
    @PostMapping(path ="/add")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody String add(@RequestBody CreditCard creditCard) {
    	return cardService.add(creditCard);
    }
    
    @GetMapping(path ="/validate/{cardNumber}")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody String validate(@PathVariable String cardNumber) {
    	String result = "CARD_INVALID";
    	boolean valid = cardAuthService.validate(cardNumber);
    	if(valid)
    		result = "CARD_VALID";
    	return result;
    }
    
}
