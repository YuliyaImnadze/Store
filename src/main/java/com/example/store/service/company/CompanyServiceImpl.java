package com.example.store.service.company;

import com.example.store.dto.company.CompanyDtoRequest;
import com.example.store.dto.company.CompanyDtoResponse;
import com.example.store.entity.Company;
import com.example.store.mapper.CompanyMapper;
import com.example.store.repository.CompanyRepository;
import com.example.store.service.common.CommonServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = "companies")
public class CompanyServiceImpl extends CommonServiceImpl<Company, CompanyDtoRequest, CompanyDtoResponse,
        CompanyRepository,
        CompanyMapper>
        implements CompanyService {


    public CompanyServiceImpl(CompanyRepository repository, CompanyMapper mapper) {
        super(repository, mapper);
    }

    @Override
    @Cacheable  // если не указать имя кэш - какое оно будет. посмотреть
    public List<CompanyDtoResponse> findAll() {
        return super.findAll();
    }

    @Override
    @CachePut
    public CompanyDtoResponse create(CompanyDtoRequest entity) {
        return super.create(entity);
    }

    @Override
    @CachePut
    public void createWithoutCheck(Company entity) {
        super.createWithoutCheck(entity);
    }

    @Override
    @CachePut
    public CompanyDtoResponse update(CompanyDtoRequest entity) {
        return super.update(entity);
    }

    @Override
    @CacheEvict(allEntries = true)
    public void delete(CompanyDtoRequest entity) {
        super.delete(entity);
    }
}
