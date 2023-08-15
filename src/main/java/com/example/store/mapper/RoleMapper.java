package com.example.store.mapper;

import com.example.store.dto.role.RoleDtoRequest;
import com.example.store.dto.role.RoleDtoResponse;
import com.example.store.entity.Role;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleMapper extends CommonMapper<Role, RoleDtoRequest, RoleDtoResponse> {

}