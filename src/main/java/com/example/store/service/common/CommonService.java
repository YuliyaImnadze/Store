package com.example.store.service.common;

import com.example.store.dto.base.BaseDtoRequest;
import com.example.store.dto.base.BaseDtoResponse;
import com.example.store.entity.BaseEntity;

import java.util.List;
import java.util.UUID;

public interface CommonService<E extends BaseEntity, D extends BaseDtoRequest, T extends BaseDtoResponse> {

    List<T> findAll();

    T findById(UUID id);

    E findByIdEntity(UUID id);

    T create(D entity);

    void createWithoutCheck(E entity);

    T update(D entity);

    void delete(D entity);


}

