package com.card.pos.remote.service;

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

import com.card.pos.remote.exception.RetryableException;
import com.card.pos.remote.model.BankPayRequest;
import com.card.pos.remote.model.BankPayResponse;
import com.card.pos.remote.util.RestApiBuilder;
import com.card.pos.remote.util.RetryHandler;

@Service
public class AcquiringRemoteService extends RemoteService implements RetryHandler {

	@Value("${url.acquiring.card.pay}")
	private String urlAcquiringCardPay;
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private RestApiBuilder restApiBuilder;

	@Retryable(value = {RetryableException.class, TimeoutException.class},
            maxAttempts = 3, backoff = @Backoff(value = 1000, multiplier = 2))
	public BankPayResponse payToMerchant(String pos, String cardNumber, String pin, Float amount) throws RetryableException {
		System.out.println("RemoteService :: payToMerchant :: {bank}, {account}, {cardNumber}, {pin}, {amount}");
		try {
			BankPayRequest bankPayRequest = new BankPayRequest(amount);
			String REST_SERVICE_URL = urlAcquiringCardPay + "/" + pos + "?cardNumber=" + cardNumber + "&pin=" + pin + "&amount=" + amount;
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
