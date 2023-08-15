package com.example.store.mapper;

import com.example.store.dto.characteristic.CharacteristicDtoRequest;
import com.example.store.dto.characteristic.CharacteristicDtoResponse;
import com.example.store.entity.Characteristic;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CharacteristicMapper extends CommonMapper<Characteristic, CharacteristicDtoRequest, CharacteristicDtoResponse> {
}
