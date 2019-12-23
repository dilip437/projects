package com.card.payment.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.card.payment.bank.entity.Account;
import com.card.payment.bank.entity.Transaction;
import com.card.payment.bank.repository.TransactionRepository;
import com.card.payment.bank.util.DateUtil;

@Service
public class TransactionService {

	@Autowired
	private DateUtil dateUtil;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private AccountService accountService;
	
    public Transaction find(String number) {
    	System.out.println("TransactionService :: find {number}");
		return transactionRepository.findByNumber(number);
    }
    
	public String pay(String accountNumber, Float amount) {
		System.out.println("TransactionService :: pay");
		return withdraw(accountNumber, amount);
	}
	
	public String deposit(String accountNumber, Float amount) {
		System.out.println("TransactionService :: deposit");
		if(perform(accountNumber, amount, true)) 
			return "DEPOSIT_SUCCESS";
		else 
			return "DEPOSIT_ERROR";
    }
	// "PAYMENT_ERROR" "PAYMENT_SUCCESS"
	public String withdraw(String accountNumber, Float amount) {
		System.out.println("TransactionService :: withdraw");
		if(perform(accountNumber, amount, false)) 
			return "PAYMENT_SUCCESS";
		else 
			return "PAYMENT_ERROR";
	}
	
	private boolean perform(String accountNumber, Float amount, Boolean inOut) {
		System.out.println("TransactionService :: perform");
		
		Account account = accountService.find(accountNumber);
		if(account == null) return false;
		if(!inOut && account.getBalance() < amount) return false;
		
		String transactionNumber = dateUtil.getTransactionNumber();
		if(transactionNumber == null || transactionNumber.length() <= 0) return false;
		
		String date = dateUtil.getCurrentDateTime();
		Float newBalance = calculateBalance(inOut, amount, account.getBalance());
		Transaction transaction = add(new Transaction(transactionNumber, account.getNumber(), date, inOut, amount, newBalance));
		if(transaction.getId() == null || transaction.getId().length() <= 0) return false;
		
		account.setBalance(transaction.getBalance());
		accountService.save(account);
		return true;
	}
	
	private Transaction add(Transaction transaction) {
		System.out.println("TransactionService :: add");
		transaction = transactionRepository.save(transaction);
    	return transaction;
    }
    
	private Float calculateBalance(Boolean inOut, Float amount, Float balance) {
		if(inOut)
			balance = balance + amount;
		else
			balance = balance - amount;
		return balance;
	}
}
