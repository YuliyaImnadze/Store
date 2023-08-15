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


    @Override
    public Set<Company> findCompaniesByIdsOrThrow(Set<UUID> companyIds) {
        Set<Company> companies = repository.findAllByIdIn(companyIds);
        // проверить по размеру. если меньше, искать
        if (companies.size() < companyIds.size()) {
            Set<UUID> foundCompanyIds = companies.stream().map(Company::getId).collect(Collectors.toSet());
            Set<UUID> missingCompanyIds = new HashSet<>(companyIds);
            missingCompanyIds.removeAll(foundCompanyIds);

            throw new EntityNotFoundException("Companies with ids: " + missingCompanyIds + " not found");
        }

        return companies;
    }

}
