package com.example.store.exception;

import org.springframework.mail.MailException;

public class EmailSendingException extends MailException {
    public EmailSendingException(String msg) {
        super(msg);
    }
}
