package com.card.payment.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.card.payment.bank.service.TransactionService;

@RestController
@RequestMapping("/bank/transaction")
public class TransactionController {

	@Autowired
    private TransactionService transactionService;

	@GetMapping(path ="/deposit/{account}/{amount}")
    @ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody String deposit(@PathVariable String account, @PathVariable Float amount) {
		System.out.println("TransactionController :: deposit :: account, amount");
		return transactionService.deposit(account, amount);
    }
	
	@GetMapping(path ="/withdraw/{account}/{amount}")
    @ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody String withdraw(@PathVariable String account, @PathVariable Float amount) {
		System.out.println("TransactionController :: withdraw :: account, amount");
		return transactionService.withdraw(account, amount);
    }
}
