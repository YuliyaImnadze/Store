package com.example.store.dto.user;

import com.example.store.dto.base.BaseDtoResponse;
import com.example.store.dto.role.RoleDtoResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;


@Data
@EqualsAndHashCode(callSuper = true)
public class UserDtoResponse extends BaseDtoResponse {

    private String username;

    private String email;

    private BigDecimal balance;

    private RoleDtoResponse role;

}
