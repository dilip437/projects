package com.card.payment.acquiring.remote.service;

import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Recover;

import com.card.payment.acquiring.remote.util.RetryHandler;

public abstract class RemoteService  implements RetryHandler {
	
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
