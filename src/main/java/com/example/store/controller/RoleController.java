package com.example.store.controller;

import com.example.store.dto.BaseResponse;
import com.example.store.dto.role.RoleDtoRequest;
import com.example.store.dto.role.RoleDtoResponse;
import com.example.store.entity.Role;
import com.example.store.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/store/role")
public class RoleController {

    private final RoleService service;

    @Autowired
    public RoleController(RoleService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public BaseResponse<RoleDtoResponse> create(@RequestBody RoleDtoRequest roleDtoRequest) {
        RoleDtoResponse roleDtoResponse = service.create(roleDtoRequest);
        return new BaseResponse<>(HttpStatus.OK, roleDtoResponse);
    }

}
