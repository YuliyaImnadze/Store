package com.example.store.exception.handler;

import com.example.store.dto.BaseResponse;
import com.example.store.exception.DiscountNotExpiredException;
import com.example.store.exception.InsufficientFundsException;
import com.example.store.exception.InsufficientStockException;
import com.example.store.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionApiHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MailException.class)
    public ResponseEntity<BaseResponse<?>> mailException(MailException exception) {
        BaseResponse<String> response = new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(DiscountNotExpiredException.class)
    public ResponseEntity<BaseResponse<?>> notExpiredPeriod(DiscountNotExpiredException exception) {
        BaseResponse<String> response = new BaseResponse<>(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<BaseResponse<?>> insufficientFunds(InsufficientFundsException exception) {
        BaseResponse<String> response = new BaseResponse<>(HttpStatus.PAYMENT_REQUIRED, exception.getMessage());
        return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body(response);
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<BaseResponse<?>> insufficientFunds(InsufficientStockException exception) {
        BaseResponse<String> response = new BaseResponse<>(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<BaseResponse<?>> insufficientFunds(ProductNotFoundException exception) {
        BaseResponse<String> response = new BaseResponse<>(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
