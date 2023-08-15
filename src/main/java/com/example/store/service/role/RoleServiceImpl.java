package com.example.store.service.role;

import com.example.store.dto.role.RoleDtoRequest;
import com.example.store.dto.role.RoleDtoResponse;
import com.example.store.entity.Role;
import com.example.store.mapper.RoleMapper;
import com.example.store.repository.RoleRepository;
import com.example.store.service.common.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl extends CommonServiceImpl<Role, RoleDtoRequest, RoleDtoResponse,
        RoleRepository,
        RoleMapper>
        implements RoleService {

    public RoleServiceImpl(RoleRepository repository, RoleMapper mapper) {
        super(repository, mapper);
    }


}
