package com.example.store.dto.productLine;


import com.example.store.dto.base.BaseDtoResponse;
import com.example.store.dto.company.CompanyDtoResponse;
import com.example.store.dto.product.ProductDtoResponse;
import com.example.store.entity.Purchase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductLineDtoResponse extends BaseDtoResponse {

    private ProductDtoResponse product; // тут только название

    private Integer count;

    private BigDecimal totalSum;

    private CompanyDtoResponse company; // тоже только название

    private Purchase purchase; // тут не ДТО и с этим нужно что то сделать

//    private ProductDtoResponse product;
//
//    private Integer count;
//
//    private BigDecimal totalSum;
//
//    private CompanyDtoResponse company;
//
//    private Purchase purchase; // тут не ДТО и с этим нужно что то сделать


}
