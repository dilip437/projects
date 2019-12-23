package com.mastercard.controller;

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

import com.mastercard.model.RegisterInfo;
import com.mastercard.service.RegisterService;

@RestController
@RequestMapping("/mastercard/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;
    
    @GetMapping(path ="/{cardNumber}")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody String register(@PathVariable String cardNumber, 
    		@RequestParam String number, @RequestParam String bank, @RequestParam String bankAccount) {
    	return registerService.add(new RegisterInfo(number, bank, bankAccount));
    }

    @PostMapping(path ="/{cardNumber}")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody String register(@PathVariable String cardNumber, 
    		@RequestBody RegisterInfo registerInfo) {
    	return registerService.add(registerInfo);
    }
}
