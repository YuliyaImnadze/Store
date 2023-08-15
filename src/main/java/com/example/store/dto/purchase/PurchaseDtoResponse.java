package com.example.store.dto.purchase;

import com.example.store.dto.base.BaseDtoResponse;
import com.example.store.dto.productLine.ProductLineDtoResponse;
import com.example.store.entity.ProductLine;
import com.example.store.entity.Purchase;
import com.example.store.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
public class PurchaseDtoResponse extends BaseDtoResponse {

    private Set<ProductLineDtoResponse> productList; // только продукт id

    private BigDecimal productListAmount;

    private User buyer;

    private LocalDate createdDate;


}
