package com.example.store.service.notification;

import com.example.store.dto.notification.NotificationDtoRequest;
import com.example.store.dto.notification.NotificationDtoResponse;
import com.example.store.entity.Notification;
import com.example.store.service.common.CommonService;

import java.util.UUID;

public interface NotificationService extends CommonService<Notification, NotificationDtoRequest, NotificationDtoResponse> {


    void createSimpleEmail(NotificationDtoRequest notificationDtoRequest);

    void createEmailWithAttachment(NotificationDtoRequest notificationDtoRequest, UUID purchaseId);

}

