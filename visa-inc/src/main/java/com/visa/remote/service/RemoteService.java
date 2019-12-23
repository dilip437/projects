package com.visa.remote.service;

import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.visa.remote.exception.RetryableException;
import com.visa.remote.model.BankAuthRequest;
import com.visa.remote.model.BankAuthResponse;
import com.visa.remote.model.BankPayRequest;
import com.visa.remote.model.BankPayResponse;
import com.visa.remote.util.RestApiBuilder;
import com.visa.remote.util.RetryHandler;

@Service
public class RemoteService  implements RetryHandler {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private RestApiBuilder restApiBuilder;

	@Retryable(value = {RetryableException.class, TimeoutException.class},
            maxAttempts = 3, backoff = @Backoff(value = 1000, multiplier = 2))
	public BankAuthResponse authorizeByIssuer(String cardNumber, Float amount) throws RetryableException {

		try {
			BankAuthRequest bankAuthRequest = new BankAuthRequest(cardNumber, amount);
			String REST_SERVICE_URL = "http://localhost:9080/bank/auth/authorize" + "/" + cardNumber + "/" + amount;
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
	public BankPayResponse payByIssuer(String cardNumber, String pin, Float amount) throws RetryableException {

		try {
			BankPayRequest bankPayRequest = new BankPayRequest(cardNumber, pin, amount);
			String REST_SERVICE_URL = "http://localhost:9080/bank/pay" + "/" + cardNumber + "/" + pin +  "/" + amount;
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
	public BankPayResponse payByIssuer(String cardNumber, String validity, String cvv, Float amount) throws RetryableException {

		try {
			BankPayRequest bankPayRequest = new BankPayRequest(cardNumber, validity, cvv, amount);
			String REST_SERVICE_URL 
				= "http://localhost:9080/bank/pay" + "/" + cardNumber + "?validity=" + validity + "&cvv=" + cvv + "&amount=" + amount;
			
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
	
	@Recover
    @Override
    public String recover(Throwable t) {
        return RetryHandler.super.recover(t);
    }

    @Override
    public <T> boolean retry(ResponseEntity<T> responseEntity) {
        return RetryHandler.super.retry(responseEntity);
    }
}
