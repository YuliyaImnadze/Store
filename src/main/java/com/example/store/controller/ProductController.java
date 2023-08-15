package com.example.store.controller;

import com.example.store.dto.BaseResponse;
import com.example.store.dto.product.ProductDtoRequest;
import com.example.store.dto.product.ProductDtoResponse;
import com.example.store.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store/product")
public class ProductController {

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<BaseResponse<ProductDtoResponse>> create(@RequestBody ProductDtoRequest productDtoRequest) {
        ProductDtoResponse productDtoResponse = service.create(productDtoRequest);
        BaseResponse<ProductDtoResponse> response = new BaseResponse<>(HttpStatus.OK, productDtoResponse);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<BaseResponse<ProductDtoResponse>> update(@RequestBody ProductDtoRequest productDtoRequest) {
        ProductDtoResponse productDtoResponse = service.update(productDtoRequest);
        BaseResponse<ProductDtoResponse> response = new BaseResponse<>(HttpStatus.OK, productDtoResponse);
        return ResponseEntity.ok(response);
    }



}