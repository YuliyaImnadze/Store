package com.example.store.service.common;

import com.example.store.dto.base.BaseDtoRequest;
import com.example.store.dto.base.BaseDtoResponse;
import com.example.store.entity.BaseEntity;
import com.example.store.mapper.CommonMapper;
import com.example.store.repository.CommonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CommonServiceImpl<E extends BaseEntity, D extends BaseDtoRequest, T extends BaseDtoResponse,
        R extends CommonRepository<E>, M extends CommonMapper<E, D, T>> implements CommonService<E, D, T> {

    protected final R repository;
    protected final M mapper;

    public CommonServiceImpl(R repository, M mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<T> findAll() {
        List<E> listEntity = repository.findAll();
        return listEntity.stream().map(mapper::toDtoResponseFromEntity).collect(Collectors.toList());
    }

    @Override
    public T findById(UUID id)  {
        E baseEntityById = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found"));
        return mapper.toDtoResponseFromEntity(baseEntityById);
    }

    @Override
    public E findByIdEntity(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found"));
    }

    @Transactional
    @Override
    public T create(D entity)  {
        E savedEntity = repository.save(mapper.toEntityFromRequest(entity));
        return mapper.toDtoResponseFromEntity(savedEntity);
    }

    @Override
    public void createWithoutCheck(E entity) {
        repository.save(entity);
    }

    @Transactional
    @Override
    public T update(D entity) {
        E updatedEntity = repository.findById(entity.getId())
                .orElseThrow(() -> new EntityNotFoundException("Entity not found"));
        mapper.partialUpdateRequest(updatedEntity,entity);
        E savedEntity = repository.save(updatedEntity);
        return mapper.toDtoResponseFromEntity(savedEntity);
    }

    @Transactional
    @Override
    public void delete(D entity) {
        repository.delete(mapper.toEntityFromRequest(entity));
    }
}
