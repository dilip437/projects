package com.card.payment.acquiring.remote.service;

import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.card.payment.acquiring.config.Constant;
import com.card.payment.acquiring.remote.exception.RetryableException;
import com.card.payment.acquiring.remote.model.BankAuthRequest;
import com.card.payment.acquiring.remote.model.BankAuthResponse;
import com.card.payment.acquiring.remote.model.BankPayRequest;
import com.card.payment.acquiring.remote.model.BankPayResponse;
import com.card.payment.acquiring.remote.util.RestApiBuilder;
import com.card.payment.acquiring.remote.util.RetryHandler;

@Service
public class VisaRemoteService extends RemoteService implements RetryHandler {

	@Value("${url.visa.card.authorize}")
	private String urlVisaCardAuthorize;
	
	@Value("${url.visa.card.pay}")
	private String urlVisaCardPay;
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private RestApiBuilder restApiBuilder;

	@Retryable(value = {RetryableException.class, TimeoutException.class},
            maxAttempts = 3, backoff = @Backoff(value = 1000, multiplier = 2))
	public BankAuthResponse authorizeByCardNetwork(String cardNumber, Float amount) throws RetryableException {
		System.out.println(Constant.AppName + " :: " + "VisaRemoteService :: authorizeByCardNetwork :: cardNumber, amount");
		try {
			BankAuthRequest bankAuthRequest = new BankAuthRequest(cardNumber, amount);
			String REST_SERVICE_URL = urlVisaCardAuthorize + "/" + cardNumber + "/" + amount;
			HttpEntity authHeader = restApiBuilder.getAuthHeader(bankAuthRequest);
			ResponseEntity<String> responseEntity =
				    restTemplate.exchange(REST_SERVICE_URL, HttpMethod.GET, authHeader, String.class);
			if (retry(responseEntity))
		     	    throw new RetryableException("Retry Failed");
			return new BankAuthResponse(responseEntity.getBody());
		} catch (Exception e) {
		    throw e;
		}
	}

	@Retryable(value = {RetryableException.class, TimeoutException.class},
            maxAttempts = 3, backoff = @Backoff(value = 1000, multiplier = 2))
	public BankPayResponse payByCardNetwork(String cardNumber, String pin, Float amount) throws RetryableException {
		System.out.println(Constant.AppName + " :: " + "VisaRemoteService :: payByCardNetwork :: cardNumber, pin, amount");
		try {
			BankPayRequest bankPayRequest = new BankPayRequest(cardNumber, pin, amount);
			String REST_SERVICE_URL = urlVisaCardPay + "/" + cardNumber + "?pin=" + pin +  "&amount=" + amount;
			HttpEntity authHeader = restApiBuilder.getAuthHeader(bankPayRequest);
			ResponseEntity<String> responseEntity =
				    restTemplate.exchange(REST_SERVICE_URL, HttpMethod.GET, authHeader, String.class);
			if (retry(responseEntity))
		     	    throw new RetryableException("Retry Failed");
			return new BankPayResponse(responseEntity.getBody());
		} catch (Exception e) {
		    throw e;
		}
	}
	
	@Retryable(value = {RetryableException.class, TimeoutException.class},
            maxAttempts = 3, backoff = @Backoff(value = 1000, multiplier = 2))
	public BankPayResponse payByCardNetwork(String cardNumber, String validity, String cvv, Float amount) throws RetryableException {
		System.out.println(Constant.AppName + " :: " + "VisaRemoteService :: payByCardNetwork :: cardNumber, validity, cvv, amount");
		try {
			BankPayRequest bankPayRequest = new BankPayRequest(cardNumber, validity, cvv, amount);
			String REST_SERVICE_URL 
				= urlVisaCardPay + "/" + cardNumber + "/" + cvv + "?validity=" + validity + "&amount=" + amount;
			HttpEntity authHeader = restApiBuilder.getAuthHeader(bankPayRequest);
			ResponseEntity<String> responseEntity =
				    restTemplate.exchange(REST_SERVICE_URL, HttpMethod.GET, authHeader, String.class);
			if (retry(responseEntity))
		     	    throw new RetryableException("Retry Failed");
			return new BankPayResponse(responseEntity.getBody());
		} catch (Exception e) {
		    throw e;
		}
	}
}
