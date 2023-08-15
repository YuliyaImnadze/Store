package com.example.store.service.notification;

import jakarta.mail.MessagingException;

import java.io.IOException;
import java.util.UUID;

public interface NotificationService { // здесь нужно доделать - тк при отправке не создается сущность


    void createSimpleEmail(String toAddress, String subject, String message);

    void createEmailWithAttachment(String toAddress, String subject, String message, UUID purchaseId)
            throws IOException, MessagingException;
}

