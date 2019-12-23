package com.acquiring.mastercard.remote.service;

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

import com.acquiring.mastercard.remote.exception.RetryableException;
import com.acquiring.mastercard.remote.model.BankPayRequest;
import com.acquiring.mastercard.remote.model.BankPayResponse;
import com.acquiring.mastercard.remote.util.RestApiBuilder;
import com.acquiring.mastercard.remote.util.RetryHandler;

@Service
public class MerchantRemoteService extends RemoteService implements RetryHandler {

	@Value("${url.merchant.bank.fund.recv}")
	private String urlMerchantBankFundRecv;
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private RestApiBuilder restApiBuilder;

	@Retryable(value = {RetryableException.class, TimeoutException.class},
            maxAttempts = 3, backoff = @Backoff(value = 1000, multiplier = 2))
	public BankPayResponse payToMerchant(String account, Float amount) throws RetryableException {
		System.out.println("MerchantRemoteService :: payToMerchant :: account, amount");
		try {
			BankPayRequest bankPayRequest = new BankPayRequest(account, amount);
			String REST_SERVICE_URL = urlMerchantBankFundRecv + "/" + account + "/" + amount;
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
