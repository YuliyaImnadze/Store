package com.example.store.repository;

import com.example.store.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface CompanyRepository extends CommonRepository<Company> {

    Set<Company> findAllByIdIn(Set<UUID> companyIds);

}
