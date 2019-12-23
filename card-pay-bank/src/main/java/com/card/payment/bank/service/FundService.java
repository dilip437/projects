package com.card.payment.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.card.payment.bank.entity.Account;
import com.card.payment.bank.entity.Transaction;
import com.card.payment.bank.repository.TransactionRepository;
import com.card.payment.bank.util.DateUtil;

import com.card.payment.bank.service.TransactionService;

@Service
public class FundService {

	@Autowired
	private TransactionService transactionService;
	
	public String recv(String account, Float amount) {
	  System.out.println("FundService :: recv :: account, amount");
	  if("DEPOSIT_SUCCESS".equals(transactionService.deposit(account, amount))) {
		return "PAYMENT_SUCCESS";
	  }
	  return "PAYMENT_ERROR";
	}
}
