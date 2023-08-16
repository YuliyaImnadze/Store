package com.example.store.controller;

import com.example.store.dto.BaseResponse;
import com.example.store.dto.purchase.PurchaseDtoRequest;
import com.example.store.dto.purchase.PurchaseDtoResponse;
import com.example.store.entity.Purchase;
import com.example.store.service.purchase.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;


@RestController
@RequestMapping("/store/purchase")
public class PurchaseController {

    private final PurchaseService service;

    @Autowired
    public PurchaseController(PurchaseService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<BaseResponse<PurchaseDtoResponse>> create(@RequestBody PurchaseDtoRequest purchaseDtoRequest) {
        PurchaseDtoResponse purchaseDtoResponse = service.create(purchaseDtoRequest);
        BaseResponse<PurchaseDtoResponse> response = new BaseResponse<>(HttpStatus.OK, purchaseDtoResponse);
        return ResponseEntity.ok(response);
    }

//    @PostMapping("/file")
//    public ResponseEntity<BaseResponse<String>> createFile(@RequestParam("purchaseId")UUID purchaseId) {
//        try {
//            service.writePurchaseToFile(purchaseId);
//            BaseResponse<String> response = new BaseResponse<>(HttpStatus.OK, "Данные о покупке успешно записаны в файл");
//            return ResponseEntity.ok(response);
//        } catch (IOException e) {
//            // 400 BAD REQUEST
//            BaseResponse<String> response = new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, "Ошибка при записи покупки в файл: " + e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//        }
//
//    }


}
