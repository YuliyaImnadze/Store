package com.example.store.service.company;

import com.example.store.dto.company.CompanyDtoRequest;
import com.example.store.dto.company.CompanyDtoResponse;
import com.example.store.entity.Company;
import com.example.store.mapper.CompanyMapper;
import com.example.store.repository.CompanyRepository;
import com.example.store.service.common.CommonServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl extends CommonServiceImpl<Company, CompanyDtoRequest, CompanyDtoResponse,
        CompanyRepository,
        CompanyMapper>
        implements CompanyService {


    public CompanyServiceImpl(CompanyRepository repository, CompanyMapper mapper) {
        super(repository, mapper);
    }


}
