package com.example.store.dto.discount;

import com.example.store.dto.base.BaseDtoRequest;
import com.example.store.dto.product.ProductDtoRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class DiscountDtoRequest extends BaseDtoRequest {

    private List<ProductDtoRequest> discountedProducts;

    private Double discount;

    private LocalDate discountPeriod;

}
