package com.visa.controller;

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

import com.visa.model.PosInfo;
import com.visa.service.CardAuthService;

@RestController
@RequestMapping("/visa/cc/auth")
public class CardAuthController {

	@Autowired
    private CardAuthService cardAuthService;

//	@PostMapping(path ="/authorize")
//    @ResponseStatus(value = HttpStatus.OK)
//    public @ResponseBody String authorize(@RequestBody PosInfo posInfo) {
//    	String result = "AUTHORIZATION_ERROR";
//    	result = cardAuthService.authorize(posInfo.getNumber(), posInfo.getPin());
//    	return result;
//    }
	
//	@GetMapping(path ="/authorize/{cardNumber}/{pin}")
//    @ResponseStatus(value = HttpStatus.OK)
//    public @ResponseBody String authorize(@PathVariable String cardNumber, @PathVariable String pin) {
//    	String result = "AUTHORIZATION_ERROR";
//    	boolean success = cardAuthService.authorize(cardNumber, pin);
//    	if(success)
//    		result = "AUTHORIZATION_SUCCESS";
//    	return result;
//    }

	@GetMapping(path ="/authorize/{cardNumber}/{amount}")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody String authorize(@PathVariable String cardNumber, @PathVariable Float amount) {
		System.out.println("CardAuthController :: authorize :: cardNumber, amount");
    	return cardAuthService.authorizeWithIssuer(cardNumber, amount);
	}
	
//	@GetMapping(path ="/authorize/{cardNumber}/{validity}/{amount}")
//    @ResponseStatus(value = HttpStatus.OK)
//    public @ResponseBody String authorize(@PathVariable String cardNumber, @PathVariable String validity, @PathVariable Float amount) {
//    	return cardAuthService.authorizeWithIssuer(cardNumber, validity, amount);
//	}
}
