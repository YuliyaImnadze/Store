package com.example.store.exception;

public class PageSizeExceedsLimitException extends RuntimeException {

    public PageSizeExceedsLimitException(String message) {
        super(message);
    }
}
