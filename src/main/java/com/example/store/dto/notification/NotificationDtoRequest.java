package com.example.store.dto.notification;

import com.example.store.dto.base.BaseDtoRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class NotificationDtoRequest extends BaseDtoRequest {

    private String header;

    private String text;

    private UUID recipientId;

//    private User sender; не знаю как сделать бин в сервисе https://www.baeldung.com/get-user-in-spring-security

}
