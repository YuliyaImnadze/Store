package com.example.store.dto.role;

import com.example.store.dto.base.BaseDtoRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.store.entity.Role}
 */


//@Value - делает final. можно ли так в dto?
//public record RoleDtoRequest(String title) implements Serializable { // какой вариант лучше??
//}
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleDtoRequest extends BaseDtoRequest implements Serializable {
    private String title;
}