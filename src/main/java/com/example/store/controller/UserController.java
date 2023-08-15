package com.example.store.controller;

import com.example.store.dto.BaseResponse;
import com.example.store.dto.user.UserDtoRequest;
import com.example.store.dto.user.UserDtoResponse;
import com.example.store.entity.User;
import com.example.store.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/store/user")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<UserDtoResponse>>> findAll() {
        List<UserDtoResponse> userDtoResponse = service.findAll();
        BaseResponse<List<UserDtoResponse>> response = new BaseResponse<>(HttpStatus.OK, userDtoResponse);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<BaseResponse<UserDtoResponse>> create(@RequestBody UserDtoRequest userDtoRequest) {
        UserDtoResponse userDtoResponse = service.create(userDtoRequest);
        BaseResponse<UserDtoResponse> response = new BaseResponse<>(HttpStatus.OK, userDtoResponse);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/update")
    public ResponseEntity<BaseResponse<UserDtoResponse>> update(@RequestBody UserDtoRequest userDtoRequest) {
        UserDtoResponse userDtoResponse = service.update(userDtoRequest);
        BaseResponse<UserDtoResponse> response = new BaseResponse<>(HttpStatus.OK, userDtoResponse);
        return ResponseEntity.ok(response);
    }


}
