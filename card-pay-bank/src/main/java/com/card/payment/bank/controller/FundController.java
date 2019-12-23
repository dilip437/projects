package com.card.payment.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.card.payment.bank.service.FundService;

@RestController
@RequestMapping("/bank/fund")
public class FundController {

	@Autowired
    private FundService fundService;

    @GetMapping(path ="/recv/{account}/{amount}")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody String recv(@PathVariable String account, @PathVariable Float amount) {
	System.out.println("FundsController :: recv :: account, amount");
	return fundService.recv(account, amount);
    }
}
