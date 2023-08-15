package com.example.store.controller;

import com.example.store.dto.BaseResponse;
import com.example.store.service.notification.NotificationService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/store/notification")
public class NotificationController {

    private final NotificationService service;

    @Autowired
    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @PostMapping(value = "/simple-email")
    public ResponseEntity<BaseResponse<String>> sendSimpleEmail(@RequestParam("user-email") String email,
                                                                @RequestParam("subject") String subject,
                                                                @RequestBody String text) {
        service.createSimpleEmail(email, subject, text);
        BaseResponse<String> response = new BaseResponse<>(HttpStatus.OK, "Please check your inbox");
        return ResponseEntity.ok(response);
    }


    @GetMapping(value = "/simple-order-email/{user-email}")
    public @ResponseBody ResponseEntity sendEmailAttachment(@PathVariable("user-email") String email,
                                                            @RequestParam("purchaseId") UUID purchaseId) {

        try {
            service.createEmailWithAttachment(email, "Order Confirmation", "Thanks for your recent order",
                    purchaseId);
        } catch (MessagingException | FileNotFoundException mailException) {
            return new ResponseEntity<>("Unable to send email", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>("Please check your inbox for order confirmation", HttpStatus.OK);
    }


//    @GetMapping(value = "/simple-order-email/{user-email}")
//    public @ResponseBody ResponseEntity sendEmailAttachment(@PathVariable("user-email") String email,
//                                                            @RequestParam("purchaseId") UUID purchaseId) {
//
//        try {
//            service.createEmailWithAttachment(email, "Order Confirmation", "Thanks for your recent order",
//                    purchaseId);
//        } catch (MessagingException | FileNotFoundException mailException) {
//            return new ResponseEntity<>("Unable to send email", HttpStatus.INTERNAL_SERVER_ERROR);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return new ResponseEntity<>("Please check your inbox for order confirmation", HttpStatus.OK);
//    }

}
