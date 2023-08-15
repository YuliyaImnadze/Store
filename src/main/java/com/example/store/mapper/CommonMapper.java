package com.example.store.mapper;

import com.example.store.dto.base.BaseDtoRequest;
import com.example.store.dto.base.BaseDtoResponse;
import com.example.store.entity.BaseEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

public interface CommonMapper<E extends BaseEntity, D extends BaseDtoRequest, T extends BaseDtoResponse> {

    E toEntityFromRequest(D dto);
    E toEntityFromResponse(T dto);

    D toDtoRequestFromEntity(E entity);
    T toDtoResponseFromEntity(E entity);

    List<E> toEntityFromRequest(List<D> dto);
    List<T> toDtoResponseFromEntity(List<E> entityList);

    @Named("partialUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdateRequest(@MappingTarget E entity, D dto);

    @Named("partialUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdateResponse(@MappingTarget E entity, T dto);

}
