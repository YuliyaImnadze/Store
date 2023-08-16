package com.example.store.service.company;

import com.example.store.dto.company.CompanyDtoRequest;
import com.example.store.dto.company.CompanyDtoResponse;
import com.example.store.entity.Company;
import com.example.store.service.common.CommonService;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface CompanyService extends CommonService<Company, CompanyDtoRequest, CompanyDtoResponse> {


}
