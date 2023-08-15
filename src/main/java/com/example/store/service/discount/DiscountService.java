package com.example.store.service.discount;

import com.example.store.dto.discount.DiscountDtoRequest;
import com.example.store.dto.discount.DiscountDtoResponse;
import com.example.store.entity.Discount;
import com.example.store.entity.Product;
import com.example.store.service.common.CommonService;

import java.util.UUID;

public interface DiscountService extends CommonService<Discount, DiscountDtoRequest, DiscountDtoResponse> {

    void checkDiscountPeriod(Discount discount);

    DiscountDtoResponse create(DiscountDtoRequest discountDtoRequest);


}

