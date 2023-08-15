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

    // create
    T create(D entity); // тут тоже вопрос - везде по разному создается. нужен этот метод зедсь вообще??

    E createWithoutCheck(E entity); // можно так?? таких ситуаций 2 на весь код. надо в этом случае в общий сервис это?

    T update(D entity);

    void delete(D entity);


}

