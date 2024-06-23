package com.example.store.controller;

import com.example.store.dto.BaseResponse;
import com.example.store.dto.purchase.PurchaseDtoRequest;
import com.example.store.dto.purchase.PurchaseDtoResponse;
import com.example.store.service.purchase.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/store/purchase")
public class PurchaseController {

    private final PurchaseService service;

    @Autowired
    public PurchaseController(PurchaseService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public BaseResponse<PurchaseDtoResponse> create(@RequestBody PurchaseDtoRequest purchaseDtoRequest) {
        PurchaseDtoResponse purchaseDtoResponse = service.create(purchaseDtoRequest);
        return new BaseResponse<>(HttpStatus.OK, purchaseDtoResponse);
    }

    @GetMapping("/byUser")
    public BaseResponse<List<PurchaseDtoResponse>> findAllPurchaseByBuyer(Authentication authentication) {
        List<PurchaseDtoResponse> allPurchaseByBuyer = service.findAllPurchaseByBuyer(authentication);
        return new BaseResponse<>(HttpStatus.OK, allPurchaseByBuyer);
    }

}
