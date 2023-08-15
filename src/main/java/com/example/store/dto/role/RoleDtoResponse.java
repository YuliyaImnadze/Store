package com.example.store.dto.role;

import com.example.store.dto.base.BaseDtoResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class RoleDtoResponse extends BaseDtoResponse implements Serializable {

    private String title;

}
