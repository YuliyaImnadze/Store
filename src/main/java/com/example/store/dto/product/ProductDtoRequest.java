package com.example.store.dto.product;

import com.example.store.dto.base.BaseDtoRequest;
import com.example.store.dto.characteristic.CharacteristicDtoRequest;
import com.example.store.dto.company.CompanyDtoRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductDtoRequest extends BaseDtoRequest {

    private String name;

    private String description;

    private CompanyDtoRequest supplier;

    private BigDecimal price;

    private Integer quantity;

    private CharacteristicDtoRequest characteristic;


}
