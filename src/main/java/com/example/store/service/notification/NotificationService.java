package com.example.store.service.notification;

import com.example.store.dto.notification.NotificationDtoRequest;
import com.example.store.dto.notification.NotificationDtoResponse;
import com.example.store.entity.Notification;
import com.example.store.service.common.CommonService;
import jakarta.mail.MessagingException;

import java.io.IOException;
import java.util.UUID;

public interface NotificationService extends CommonService<Notification, NotificationDtoRequest, NotificationDtoResponse> {


    void createSimpleEmail(NotificationDtoRequest notificationDtoRequest);

    // здесь нужно доделать - тк при отправке не создается сущность
    void createEmailWithAttachment(NotificationDtoRequest notificationDtoRequest, UUID purchaseId);
}

