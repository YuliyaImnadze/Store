package com.example.store.dto.productLine;

import com.example.store.dto.base.BaseDtoRequest;
import com.example.store.dto.company.CompanyDtoRequest;
import com.example.store.dto.product.ProductDtoRequest;
import com.example.store.entity.Company;
import com.example.store.entity.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;


@Data
@EqualsAndHashCode(callSuper = true)
public class ProductLineDtoRequest extends BaseDtoRequest {

    private UUID productId;

    private Integer count;

//    private UUID companyId;




}
