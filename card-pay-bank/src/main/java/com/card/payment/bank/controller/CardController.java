package com.card.payment.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.card.payment.bank.entity.Card;
import com.card.payment.bank.service.CardService;

@RestController
@RequestMapping("/bank/card")
public class CardController {

    @Autowired
    private CardService cardService;
    
    @GetMapping(path ="/add")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody String add(@RequestParam String number, @RequestParam String name, @RequestParam String expiryDate, 
    						@RequestParam String pin, @RequestParam String cvv, @RequestParam String account) {
    	return cardService.add(new Card(name, number, expiryDate, pin, cvv, account));
    }
    
    @PutMapping(path ="/save")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody String save(@PathVariable String number, @RequestParam String name, @RequestParam String expiryDate, 
    						@RequestParam String pin, @RequestParam String cvv, @RequestParam String account) {
    	return cardService.save(new Card(name, number, expiryDate, pin, cvv, account));
    }
    
    @GetMapping(path ="/find/{number}")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody Card find(@PathVariable String number) {
    	return cardService.find(number);
    }

}
