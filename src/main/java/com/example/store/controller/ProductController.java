package com.example.store.controller;

import com.example.store.dto.BaseResponse;
import com.example.store.dto.product.ProductDtoRequest;
import com.example.store.dto.product.ProductDtoResponse;
import com.example.store.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/store/product")
public class ProductController {

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public BaseResponse<List<ProductDtoResponse>> findAll() {
        List<ProductDtoResponse> productDtoResponses = service.findAll();
        return new BaseResponse<>(HttpStatus.OK, productDtoResponses);
    }

    @GetMapping("/findById")
    public BaseResponse<ProductDtoResponse> findBuId(@RequestParam("productId") UUID productId) {
        ProductDtoResponse productDtoResponse = service.findById(productId);
        return new BaseResponse<>(HttpStatus.OK, productDtoResponse);
    }

    @PostMapping("/create")
    public BaseResponse<ProductDtoResponse> create(@RequestBody ProductDtoRequest productDtoRequest) {
        ProductDtoResponse productDtoResponse = service.create(productDtoRequest);
        return new BaseResponse<>(HttpStatus.OK, productDtoResponse);
    }

    @PutMapping("/update")
    public BaseResponse<ProductDtoResponse> update(@RequestBody ProductDtoRequest productDtoRequest) {
        ProductDtoResponse productDtoResponse = service.update(productDtoRequest);
        return new BaseResponse<>(HttpStatus.OK, productDtoResponse);
    }

    @DeleteMapping("/delete")
    public BaseResponse<String> delete(@RequestBody ProductDtoRequest productDtoRequest) {
        service.delete(productDtoRequest);
        return new BaseResponse<>(HttpStatus.OK, "Product was deleted");
    }



}