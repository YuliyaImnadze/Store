package com.example.store.mapper;

import com.example.store.dto.purchase.PurchaseDtoRequest;
import com.example.store.dto.purchase.PurchaseDtoResponse;
import com.example.store.entity.Purchase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PurchaseMapper extends CommonMapper<Purchase, PurchaseDtoRequest, PurchaseDtoResponse> {

    @Override
    @Mapping(target = "buyer.id", source = "buyerId")
    Purchase toEntityFromRequest(PurchaseDtoRequest dto);
}
