package com.example.store.dto.notification;

import com.example.store.dto.base.BaseDtoResponse;
import com.example.store.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
public class NotificationDtoResponse extends BaseDtoResponse {

    private String header;

    private LocalDate date;

    private String text;

    private User recipient;

}
