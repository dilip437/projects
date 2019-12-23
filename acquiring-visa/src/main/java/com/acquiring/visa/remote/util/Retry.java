package com.acquiring.visa.remote.util;

import org.springframework.http.ResponseEntity;

/**
 * @author s0n00e1
 */
public interface Retry {

    default <T> boolean retry(ResponseEntity<T> responseEntity) {
        return responseEntity.getStatusCode().is5xxServerError();
    }
}
