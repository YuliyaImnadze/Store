package com.example.store.controller;

import com.example.store.dto.BaseResponse;
import com.example.store.dto.reviews.ReviewsDtoRequest;
import com.example.store.dto.reviews.ReviewsDtoResponse;
import com.example.store.service.reviews.ReviewsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/store/reviews")
public class ReviewsController {

    private final ReviewsService service;

    public ReviewsController(ReviewsService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public BaseResponse<ReviewsDtoResponse> create(@RequestParam("userId") UUID userId,
                                                                   @RequestParam("productId") UUID productId,
                                                                   @RequestBody ReviewsDtoRequest reviewsDtoRequest) {
        ReviewsDtoResponse reviewsDtoResponse = service.create(userId, productId, reviewsDtoRequest);
        return new BaseResponse<>(HttpStatus.OK, reviewsDtoResponse);
    }

}
