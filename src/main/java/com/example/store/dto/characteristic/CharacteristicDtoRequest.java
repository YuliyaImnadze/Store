package com.example.store.dto.characteristic;

import com.example.store.dto.base.BaseDtoRequest;
import com.example.store.dto.product.ProductDtoRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CharacteristicDtoRequest extends BaseDtoRequest {

    private Double weight;

    private String country; // страна производитель

    private Double height;

    private ProductDtoRequest product; // не нужно, тк отдельно не создается же. или нужно, ведь  могут быть изменения!

}
