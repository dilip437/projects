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

import com.card.payment.acquiring.entity.BankInfo;
import com.card.payment.acquiring.service.BankService;

@RestController
@RequestMapping("/acquiring/bank")
public class BankController {

    @Autowired
    private BankService bankService;
    
    @GetMapping(path ="/register")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody String register(@RequestParam String code, @RequestParam String account) {
    	return bankService.add(new BankInfo(code, account));
    }

    @GetMapping(path ="/register2")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody String register(@RequestParam String code, @RequestParam String bank, @RequestParam String account) {
    	return bankService.add(new BankInfo(code, bank, account));
    }
    
    @GetMapping(path ="/find/{account}")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody BankInfo find(@PathVariable String account) {
    	return bankService.find(account);
    }
}
