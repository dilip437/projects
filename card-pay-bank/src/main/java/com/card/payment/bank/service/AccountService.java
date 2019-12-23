package com.card.payment.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.card.payment.bank.entity.Account;
import com.card.payment.bank.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	public List<Account> findAll() {
    		List<Account> list = accountRepository.findAll();
        	return list;   
	}

	public String add(Account account) {
		System.out.println("AccountService :: add");
		account = accountRepository.save(account);
    	return account.getId();
    }
	
	public String save(Account account) {
		System.out.println("AccountService :: save");
		account = accountRepository.save(account);
    	return account.getId();
    }
	
    public Account find(String number) {
    	System.out.println("AccountService :: find {number}");
		return accountRepository.findByNumber(number);
    }
}
