package com.card.payment.acquiring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.card.payment.acquiring.entity.RegisterInfo;
import com.card.payment.acquiring.service.RegisterService;

@RestController
@RequestMapping("/acquiring/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;
    
    @GetMapping(path ="/{code}/{account}")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody String register(@PathVariable String code, @PathVariable String account) {
    	return registerService.add(new RegisterInfo(code, account));
    }

}
