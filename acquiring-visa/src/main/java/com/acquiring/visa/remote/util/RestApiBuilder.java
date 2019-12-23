package com.acquiring.visa.remote.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class RestApiBuilder {
    public <T> HttpEntity getAuthHeader(T t) {
	HttpHeaders headers = new HttpHeaders();
//	headers.add("client_id", client_id);
//	headers.add("secret", secret);
	HttpEntity<?> httpEntity = new HttpEntity<Object>(t, headers);
	return httpEntity;
    }
}
