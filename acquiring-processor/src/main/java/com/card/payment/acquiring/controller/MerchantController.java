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

import com.card.payment.acquiring.entity.MerchantInfo;
import com.card.payment.acquiring.service.MerchantService;

@RestController
@RequestMapping("/acquiring/merchant")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;
    
    @GetMapping(path ="/register")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody String register(@RequestParam String code, @RequestParam String name, @RequestParam String bank, @RequestParam String account) {
    	return merchantService.add(new MerchantInfo(code, name, bank, account));
    }

    @GetMapping(path ="/register2")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody String register(@RequestParam String code, @RequestParam String bank, @RequestParam String account) {
    	return merchantService.add(new MerchantInfo(code, bank, account));
    }
    
    @GetMapping(path ="/find/{code}")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody MerchantInfo find(@PathVariable String code) {
    	return merchantService.find(code);
    }
    
    @GetMapping(path ="/findby/ac/{account}")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody MerchantInfo findByAccount(@PathVariable String account) {
    	return merchantService.findByAccount(account);
    }
}
