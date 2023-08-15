package com.example.store.dto.characteristic;

import com.example.store.dto.base.BaseDtoResponse;
import com.example.store.dto.product.ProductDtoRequest;
import com.example.store.dto.product.ProductDtoResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CharacteristicDtoResponse extends BaseDtoResponse {

    private Double weight;

    private String country; // страна производитель

    private Double height;

    private ProductDtoResponse product;

}
