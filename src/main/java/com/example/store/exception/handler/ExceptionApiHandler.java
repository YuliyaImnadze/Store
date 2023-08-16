package com.example.store.exception.handler;

import com.example.store.dto.BaseResponse;
import com.example.store.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionApiHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MailException.class)
    public BaseResponse<String> mailException(MailException exception) {
        return new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }

    @ExceptionHandler(DiscountNotExpiredException.class)
    public BaseResponse<String> notExpiredPeriod(DiscountNotExpiredException exception) {
        return new BaseResponse<>(HttpStatus.CONFLICT, exception.getMessage());
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public BaseResponse<String> insufficientFunds(InsufficientFundsException exception) {
        return new BaseResponse<>(HttpStatus.PAYMENT_REQUIRED, exception.getMessage());
    }

    @ExceptionHandler(InsufficientStockException.class)
    public BaseResponse<String> handleOutOfStockException(InsufficientStockException exception) {
        return new BaseResponse<>(HttpStatus.CONFLICT, exception.getMessage());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public BaseResponse<String> handleMissingProductException(ProductNotFoundException exception) {
        return new BaseResponse<>(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(PurchaseFileWritingException.class)
    public BaseResponse<String> handlePurchaseFileWritingException(PurchaseFileWritingException exception) {
        return new BaseResponse<>(HttpStatus.NOT_FOUND, exception.getMessage());
    }


}
