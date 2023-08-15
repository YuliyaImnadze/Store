package com.example.store.mapper;

import com.example.store.dto.user.UserDtoRequest;
import com.example.store.dto.user.UserDtoResponse;
import com.example.store.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper extends CommonMapper<User, UserDtoRequest, UserDtoResponse> {

    @Override
    @Mapping(target = "role.id", source = "roleId")
    User toEntityFromRequest(UserDtoRequest dto);
}
