package com.example.store.mapper;

import com.example.store.dto.productLine.ProductLineDtoRequest;
import com.example.store.dto.productLine.ProductLineDtoResponse;
import com.example.store.entity.ProductLine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductLineMapper extends CommonMapper<ProductLine, ProductLineDtoRequest, ProductLineDtoResponse> {

    @Override
    @Mapping(target = "product.id", source = "productId")
    @Mapping(target = "company.id", source = "companyId")
    ProductLine toEntityFromRequest(ProductLineDtoRequest dto);


    Set<ProductLine> toEntitySetFromRequest(Set<ProductLineDtoRequest> dto);


}
