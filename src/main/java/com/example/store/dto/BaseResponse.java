package com.example.store.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class BaseResponse<T> {

    private HttpStatus status;
    private T body;
    private LocalDateTime timestamp;

    public BaseResponse(HttpStatus status, T body) {
        this.status = status;
        this.body = body;
        this.timestamp = LocalDateTime.now();
    }
}
