package com.example.store.controller;

import com.example.store.dto.BaseResponse;
import com.example.store.dto.company.CompanyDtoRequest;
import com.example.store.dto.company.CompanyDtoResponse;
import com.example.store.entity.Company;
import com.example.store.service.company.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store/company")
@Slf4j
public class CompanyController {

    private final CompanyService service;

    @Autowired
    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @GetMapping
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public BaseResponse<List<CompanyDtoResponse>> showAll() {
        List<CompanyDtoResponse> companyDtoResponses = service.findAll();
        return new BaseResponse<>(HttpStatus.OK, companyDtoResponses);
    }

    @PostMapping("/create")
    public BaseResponse<CompanyDtoResponse> create(@RequestBody CompanyDtoRequest companyDtoRequest) {
        CompanyDtoResponse companyDtoResponse = service.create(companyDtoRequest);
        return new BaseResponse<>(HttpStatus.OK, companyDtoResponse);
    }

    @PutMapping("/update")
    public BaseResponse<CompanyDtoResponse> update(@RequestBody CompanyDtoRequest companyDtoRequest) {
        CompanyDtoResponse companyDtoResponse = service.update(companyDtoRequest);
        return new BaseResponse<>(HttpStatus.OK, companyDtoResponse);
    }

    @DeleteMapping("/delete")
    public BaseResponse<String> delete(@RequestBody CompanyDtoRequest companyDtoRequest) {
        service.delete(companyDtoRequest);
        return new BaseResponse<>(HttpStatus.OK, "Company was deleted");
    }

}
