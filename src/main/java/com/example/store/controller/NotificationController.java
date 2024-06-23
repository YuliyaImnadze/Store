package com.example.store.controller;

import com.example.store.dto.BaseResponse;
import com.example.store.dto.notification.NotificationDtoRequest;
import com.example.store.service.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/store/notification") // /api/v1/store/notification
public class NotificationController {

    private final NotificationService service;

    @Autowired
    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @PostMapping(value = "/simple-email")
    public BaseResponse<String> sendSimpleEmail(@RequestBody NotificationDtoRequest notificationDtoRequest) {
        service.createSimpleEmail(notificationDtoRequest);
        return new BaseResponse<>(HttpStatus.OK, "Please check your inbox");
    }

    @GetMapping(value = "/simple-order-email")
    public BaseResponse<String> sendEmailAttachment(@RequestBody NotificationDtoRequest notificationDtoRequest,
                                                    @RequestParam("purchaseId") UUID purchaseId) {
            service.createEmailWithAttachment(notificationDtoRequest, purchaseId);
        return new BaseResponse<>(HttpStatus.OK, "Please check your inbox for order confirmation");
    }


}
