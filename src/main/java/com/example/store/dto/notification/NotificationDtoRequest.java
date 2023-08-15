package com.example.store.dto.notification;

import com.example.store.dto.base.BaseDtoRequest;
import com.example.store.entity.User;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class NotificationDtoRequest extends BaseDtoRequest {

    private String header;

    private String text;

    private UUID recipientId;

//    private User sender; не знаю как сделать бин в сервисе https://www.baeldung.com/get-user-in-spring-security

}
