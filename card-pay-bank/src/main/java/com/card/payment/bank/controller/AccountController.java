package com.card.payment.bank.controller;

import java.util.List;

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

import com.card.payment.bank.entity.Account;
import com.card.payment.bank.service.AccountService;

@RestController
@RequestMapping("/bank/ac")
public class AccountController {

    @Autowired
    private AccountService accountService;
    
    @GetMapping(path ="/all")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody List<Account> findAll() {
        return accountService.findAll();
    }

    @GetMapping(path ="/add")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody String add(@RequestParam String number, @RequestParam String name, @RequestParam String bank, 
    						@RequestParam String ifsc, @RequestParam Float balance) {
    	return accountService.add(new Account(number, name, bank, ifsc, balance));
    }
    
    @PutMapping(path ="/save/{number}")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody String save(@PathVariable String number, @RequestParam String name, @RequestParam String bank, 
    						@RequestParam String ifsc, @RequestParam Float balance) {
    	return accountService.add(new Account(number, name, bank, ifsc, balance));
    }
    
    @GetMapping(path ="/find/{number}")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody Account find(@PathVariable String number) {
    	return accountService.find(number);
    }
}
