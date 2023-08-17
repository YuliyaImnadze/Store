package com.example.store.dto.discount;

import com.example.store.dto.base.BaseDtoResponse;
import com.example.store.entity.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

@Data
public class DiscountDtoResponse extends BaseDtoResponse {

    private List<Product> discountedProducts; // здесь только id

    private Double discount;

    private LocalDate discountPeriod;


}
