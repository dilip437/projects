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

import com.card.payment.acquiring.entity.PosInfo;
import com.card.payment.acquiring.service.PosService;

@RestController
@RequestMapping("/acquiring/pos")
public class PosController {

    @Autowired
    private PosService posService;

    @GetMapping(path ="/register")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody String register(@RequestParam String code, @RequestParam String merchant, @RequestParam String bank, @RequestParam String account) {
    	return posService.add(new PosInfo(code, merchant, bank, account));
    }
    
    @GetMapping(path ="/register2")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody String register(@RequestParam String code, @RequestParam String account) {
    	return posService.add(new PosInfo(code, account));
    }

    @GetMapping(path ="/find/{code}")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody PosInfo find(@PathVariable String code) {
    	return posService.find(code);
    }
    
    @GetMapping(path ="/findby/ac/{account}")
    @ResponseStatus(value = HttpStatus.OK)
    public PosInfo findByAccount(@PathVariable String account) {
    	return posService.findByAccount(account);
    }
}
