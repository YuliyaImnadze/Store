package com.example.store.service.reviews;

import com.example.store.dto.reviews.ReviewsDtoRequest;
import com.example.store.dto.reviews.ReviewsDtoResponse;
import com.example.store.entity.Reviews;
import com.example.store.service.common.CommonService;

import java.util.UUID;

public interface ReviewsService extends CommonService<Reviews, ReviewsDtoRequest, ReviewsDtoResponse> {

    ReviewsDtoResponse create(UUID userId, UUID productId, ReviewsDtoRequest reviewsDtoRequest);


}
