package com.mastercard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mastercard.model.CardSecurityInfo;
import com.mastercard.service.CardSecurityInfoService;

@RestController
@RequestMapping("/mastercard/cc/security")
public class CardSecurityInfoController {

	@Autowired
	private CardSecurityInfoService cardSecurityInfoService;
	
	@GetMapping(path ="/add")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody String add(@RequestBody CardSecurityInfo cardSecurityInfo) {
    	return cardSecurityInfoService.add(cardSecurityInfo);
    }
    
	@GetMapping(path ="/add/{cardNumber}/{pin}/{validity}")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody String add(@PathVariable String cardNumber, @PathVariable String pin, @PathVariable String validity) {
    	return cardSecurityInfoService.add(new CardSecurityInfo(cardNumber, pin, validity));
    }
    
    @GetMapping(path ="/verify/{cardNumber}/{pin}")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody String verify(@PathVariable String cardNumber, @PathVariable String pin) {
    	String result = "FALSE";
    	boolean valid = cardSecurityInfoService.verify(cardNumber, pin);
    	if(valid)
    		result = "TRUE";
    	return result;
    }
}
