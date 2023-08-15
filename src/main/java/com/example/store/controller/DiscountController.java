package com.example.store.controller;

import com.example.store.dto.BaseResponse;
import com.example.store.dto.discount.DiscountDtoRequest;
import com.example.store.dto.discount.DiscountDtoResponse;
import com.example.store.service.discount.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/store/discount")
public class DiscountController {

    private final DiscountService service;

    @Autowired
    public DiscountController(DiscountService service) {
        this.service = service;
    }

    @PostMapping("/create")
        public ResponseEntity<BaseResponse<DiscountDtoResponse>> create(@RequestBody DiscountDtoRequest discountDtoRequest) {
        DiscountDtoResponse discountDtoResponse = service.create(discountDtoRequest);
        BaseResponse<DiscountDtoResponse> baseResponse = new BaseResponse<>(HttpStatus.OK, discountDtoResponse);
        return ResponseEntity.ok(baseResponse);
    }

    @PutMapping("/update")
    public ResponseEntity<BaseResponse<DiscountDtoResponse>> update(@RequestBody DiscountDtoRequest discountDtoRequest) {
        DiscountDtoResponse discountDtoResponse = service.update(discountDtoRequest);
        BaseResponse<DiscountDtoResponse> baseResponse = new BaseResponse<>(HttpStatus.OK, discountDtoResponse);
        return ResponseEntity.ok(baseResponse);
    }


}
