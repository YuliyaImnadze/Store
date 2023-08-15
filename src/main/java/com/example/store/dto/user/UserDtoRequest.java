package com.example.store.dto.user;

import com.example.store.dto.base.BaseDtoRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDtoRequest extends BaseDtoRequest {

    private String username;

    private String email;

    private String password;

    private BigDecimal balance;

    private UUID roleId;

//    private RoleDtoRequest role;  // у 1 юзера много ролей, у 1 роли много юзеров. нужно ли лист юзеров роли?

}
