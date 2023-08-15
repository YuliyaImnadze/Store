package com.example.store.dto.reviews;

import com.example.store.dto.base.BaseDtoResponse;
import com.example.store.entity.Product;
import lombok.Data;

@Data
public class ReviewsDtoResponse extends BaseDtoResponse {

    private String reviews;

    private Integer grade;


}
