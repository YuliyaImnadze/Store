package com.example.store.dto.product;

import com.example.store.dto.base.BaseDtoResponse;
import com.example.store.dto.characteristic.CharacteristicDtoRequest;
import com.example.store.dto.company.CompanyDtoRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductDtoResponse extends BaseDtoResponse {

    private String name;

    private String description;

    private CompanyDtoRequest supplier; // здесь должно быть только id

    private BigDecimal price;

    private Integer quantity;

    private CharacteristicDtoRequest characteristic; // у характеристики не должно быть id

}
