package com.example.store.dto.base;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public abstract class BaseDtoRequest implements Serializable {

    private UUID id;

}
