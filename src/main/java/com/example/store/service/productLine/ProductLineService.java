package com.example.store.service.productLine;

import com.example.store.dto.productLine.ProductLineDtoRequest;
import com.example.store.dto.productLine.ProductLineDtoResponse;
import com.example.store.entity.Product;
import com.example.store.entity.ProductLine;
import com.example.store.service.common.CommonService;

import java.util.Set;

public interface ProductLineService extends CommonService<ProductLine, ProductLineDtoRequest, ProductLineDtoResponse> {

    ProductLine checkQuantityInProductLine(ProductLine productLine);

    ProductLine calculateTotalAmountByProductLines(ProductLine productLine);


}
