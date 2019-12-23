package com.acquiring.mastercard.remote.exception;

/**
 * @author s0n00e1
 */

public class RetryableException extends Exception {

    public RetryableException() {
    }

    public RetryableException(String message) {
        super(message);
    }

    public RetryableException(Exception e) {
        super(e);
    }
}
