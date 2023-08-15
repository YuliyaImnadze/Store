package com.example.store.dto.company;

import com.example.store.dto.base.BaseDtoRequest;
import com.example.store.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class CompanyDtoRequest extends BaseDtoRequest {

    private String name;

    private String description;

    private String logo;

    private User owner;

}
