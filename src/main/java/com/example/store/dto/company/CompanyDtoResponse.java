package com.example.store.dto.company;

import com.example.store.dto.base.BaseDtoResponse;
import com.example.store.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CompanyDtoResponse extends BaseDtoResponse {

    private String name;

    private String description;

    private String logo;

}
