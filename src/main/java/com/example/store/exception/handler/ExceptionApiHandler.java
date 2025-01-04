package com.example.store.exception.handler;

import com.example.store.dto.BaseResponse;
import com.example.store.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;

@RestControllerAdvice
public class ExceptionApiHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MailException.class)
    public ResponseEntity<BaseResponse<String>> mailException(MailException exception) {
        BaseResponse<String> response = new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(DiscountNotExpiredException.class)
    public ResponseEntity<BaseResponse<String>> notExpiredPeriod(DiscountNotExpiredException exception) {
        BaseResponse<String> response = new BaseResponse<>(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<BaseResponse<String>> insufficientFunds(InsufficientFundsException exception) {
        BaseResponse<String> response = new BaseResponse<>(HttpStatus.PAYMENT_REQUIRED, exception.getMessage());
        return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body(response);
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<BaseResponse<String>> handleOutOfStockException(InsufficientStockException exception) {
        BaseResponse<String> response = new BaseResponse<>(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<BaseResponse<String>> handleMissingProductException(ProductNotFoundException exception) {
        BaseResponse<String> response = new BaseResponse<>(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(PurchaseFileWritingException.class)
    public ResponseEntity<BaseResponse<String>> handlePurchaseFileWritingException(PurchaseFileWritingException exception) {
        BaseResponse<String> response = new BaseResponse<>(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(PageSizeExceedsLimitException.class)
    public ResponseEntity<BaseResponse<String>> pageSizeLimitException(PageSizeExceedsLimitException exception) {
        BaseResponse<String> response = new BaseResponse<>(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<BaseResponse<String>> handlePSQLException(SQLException exception) {
        BaseResponse<String> response = new BaseResponse<>(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
