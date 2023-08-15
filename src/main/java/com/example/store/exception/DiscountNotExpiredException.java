package com.example.store.exception;

public class DiscountNotExpiredException extends RuntimeException {
    public DiscountNotExpiredException(String message) {
        super(message);
    }
}
