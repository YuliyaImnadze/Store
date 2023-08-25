package com.example.store.dto.reviews;

import com.example.store.dto.base.BaseDtoRequest;
import com.example.store.dto.product.ProductDtoRequest;
import com.example.store.entity.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ReviewsDtoRequest extends BaseDtoRequest {

    private String reviews;

    private Integer grade;


}
