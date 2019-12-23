package com.acquiring.mastercard.controller;

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

import com.acquiring.mastercard.config.Constant;
import com.acquiring.mastercard.model.PosVO;
import com.acquiring.mastercard.service.CardAuthService;

@RestController
@RequestMapping("/acquiring/mastercard/auth")
public class CardAuthController {

	@Autowired
    private CardAuthService cardAuthService;

	@PostMapping(path ="/authorize")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody String authorize(@RequestBody PosVO posVO) {
    	String result = "AUTHORIZATION_ERROR";
    	result = cardAuthService.authorizeByCardNetwork(posVO.getNumber(), posVO.getAmount());
    	return result;
    }

	@GetMapping(path ="/authorize/{cardNumber}/{amount}")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody String authorize(@PathVariable String cardNumber, @PathVariable Float amount) {
		System.out.println(Constant.AppName + " :: " + "CardAuthController :: authorize :: cardNumber, amount");
    	return cardAuthService.authorizeByCardNetwork(cardNumber, amount);
	}
}
