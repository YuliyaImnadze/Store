package com.example.store.controller;

import com.example.store.dto.BaseResponse;
import com.example.store.dto.company.CompanyDtoRequest;
import com.example.store.dto.company.CompanyDtoResponse;
import com.example.store.entity.Company;
import com.example.store.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store/company")
public class CompanyController {

    private final CompanyService service;

    @Autowired
    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<CompanyDtoResponse>>> showAll() {
        List<CompanyDtoResponse> companyDtoResponses = service.findAll();
        BaseResponse<List<CompanyDtoResponse>> baseResponse = new BaseResponse<>(HttpStatus.OK, companyDtoResponses);
        return ResponseEntity.ok(baseResponse);
    }

    @PostMapping("/create")
    public ResponseEntity<BaseResponse<CompanyDtoResponse>> create(@RequestBody CompanyDtoRequest companyDtoRequest) {
        CompanyDtoResponse companyDtoResponse = service.create(companyDtoRequest);
        BaseResponse<CompanyDtoResponse> baseResponse = new BaseResponse<>(HttpStatus.OK, companyDtoResponse);
        return ResponseEntity.ok(baseResponse);
    }

}
