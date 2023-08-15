package com.example.store.dto.purchase;

import com.example.store.dto.base.BaseDtoRequest;
import com.example.store.dto.productLine.ProductLineDtoRequest;
import com.example.store.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class PurchaseDtoRequest extends BaseDtoRequest {

    private Set<ProductLineDtoRequest> productList; // у одной покупки может быть много прод.лайнов

    private UUID buyerId;


}
