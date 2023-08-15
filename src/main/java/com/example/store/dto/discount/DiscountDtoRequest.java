package com.example.store.dto.discount;

import com.example.store.dto.base.BaseDtoRequest;
import com.example.store.dto.product.ProductDtoRequest;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DiscountDtoRequest extends BaseDtoRequest {

    private List<ProductDtoRequest> discountedProducts; // сделать как то передачу только id

    private Double discount;

    private LocalDate discountPeriod;

}
