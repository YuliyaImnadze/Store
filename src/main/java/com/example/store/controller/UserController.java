package com.example.store.controller;

import com.example.store.dto.BaseResponse;
import com.example.store.dto.user.UserDtoRequest;
import com.example.store.dto.user.UserDtoResponse;
import com.example.store.entity.User;
import com.example.store.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public BaseResponse<List<UserDtoResponse>> findAll() {
        List<UserDtoResponse> userDtoResponse = service.findAll();
        return new BaseResponse<>(HttpStatus.OK, userDtoResponse);
    }

    @GetMapping ("/")
    public BaseResponse<Page<UserDtoResponse>> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                                       @RequestParam(value = "size", defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserDtoResponse> userDtoResponse = service.findAll(pageable);
        return new BaseResponse<>(HttpStatus.OK, userDtoResponse);
    }

    @GetMapping("/count")
    public BaseResponse<Long> totalCount() {
        Long count = service.totalCount();
        return new BaseResponse<>(HttpStatus.OK, count);
    }
    // отдельный метод на вывод общего кол-ва. обязательно использовать распределенные кэш

    @PostMapping("/create")
    public BaseResponse<UserDtoResponse> create(@RequestBody UserDtoRequest userDtoRequest) {
        UserDtoResponse userDtoResponse = service.create(userDtoRequest);
        return new BaseResponse<>(HttpStatus.OK, userDtoResponse);
    }

    @PostMapping("/update")
    public BaseResponse<UserDtoResponse> update(@RequestBody UserDtoRequest userDtoRequest) {
        UserDtoResponse userDtoResponse = service.update(userDtoRequest);
        return new BaseResponse<>(HttpStatus.OK, userDtoResponse);
    }


}
