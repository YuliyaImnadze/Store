package com.example.store.service.notification;

import com.example.store.dto.notification.NotificationDtoRequest;
import com.example.store.dto.notification.NotificationDtoResponse;
import com.example.store.entity.Notification;
import com.example.store.entity.Purchase;
import com.example.store.entity.User;
import com.example.store.exception.EmailSendingException;
import com.example.store.exception.PurchaseFileWritingException;
import com.example.store.mapper.NotificationMapper;
import com.example.store.repository.NotificationRepository;
import com.example.store.repository.PurchaseRepository;
import com.example.store.service.common.CommonServiceImpl;
import com.example.store.service.purchase.PurchaseService;
import com.example.store.service.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class NotificationServiceImpl extends CommonServiceImpl<Notification, NotificationDtoRequest, NotificationDtoResponse,
        NotificationRepository,
        NotificationMapper>
        implements NotificationService {

    private final JavaMailSender emailSender;
    private final UserService userService;
    private final PurchaseService purchaseService;

    @Value("${file.path}")
    private String path;

    @Autowired
    public NotificationServiceImpl(NotificationRepository repository, NotificationMapper mapper, JavaMailSender emailSender, UserService userService, PurchaseService purchaseService) {
        super(repository, mapper);
        this.emailSender = emailSender;
        this.userService = userService;
        this.purchaseService = purchaseService;
    }


    //    (String toAddress, String subject, String message)
    @Override
    public void createSimpleEmail(NotificationDtoRequest notificationDtoRequest) {
        UUID recipientId = notificationDtoRequest.getRecipientId();
        User recipient = userService.findByIdEntity(recipientId);
        String email = recipient.getEmail();
        String subject = notificationDtoRequest.getHeader();
        String message = notificationDtoRequest.getText();

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        simpleMailMessage.setFrom("testforjava@yandex.ru");
        try {
            emailSender.send(simpleMailMessage);
            Notification entity = mapper.toEntityFromRequest(notificationDtoRequest);
            repository.save(entity);
        } catch (MailException mailException) {
            throw new EmailSendingException("Unable to send email");
        }
    }


    @Override
    public void createEmailWithAttachment(NotificationDtoRequest notificationDtoRequest, UUID purchaseId) {
        writePurchaseToFile(purchaseId);
        String attachment = purchaseId + ".json";
        UUID recipientId = notificationDtoRequest.getRecipientId();
        User recipient = userService.findByIdEntity(recipientId);
        String email = recipient.getEmail();
        String subject = notificationDtoRequest.getHeader();
        String message = notificationDtoRequest.getText();
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper messageHelper = null;
        try {
            messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setTo(email);
            messageHelper.setFrom("testforjava@yandex.ru");
            messageHelper.setSubject(subject);
            messageHelper.setText(message);
            String filePath = "src/main/resources/data/" + attachment;
            FileSystemResource fileSystemResource = new FileSystemResource(filePath);
            messageHelper.addAttachment("Purchase Order", fileSystemResource);

            emailSender.send(mimeMessage);
            Notification entity = mapper.toEntityFromRequest(notificationDtoRequest);
            repository.save(entity);
        } catch (MessagingException e) {
            throw new EmailSendingException("Unable to send email");
        }
    }

    // а в этом случае пустой build -нет. метод этот где должен быть вообще? приватный в NotificationServiceImpl или тут

    private void writePurchaseToFile(UUID purchaseId) {
        Purchase purchase = purchaseService.findByIdEntity(purchaseId);
        ObjectMapper objectMapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
        String currentWorkingDirectory = System.getProperty("user.dir");
        String filePath = currentWorkingDirectory + path + purchaseId + ".json";
        try {
            objectMapper.writeValue(new File(filePath), purchase);
        } catch (IOException e) {
            throw new PurchaseFileWritingException(e.getMessage());
        }
    }

//    public void createEmailWithAttachment(String toAddress, String subject, String message, UUID purchaseId)
//            throws IOException {
//        purchaseRepository.findById(purchaseId)
//                .orElseThrow(() -> new EntityNotFoundException("Purchase not found"));
//
//        purchaseService.writePurchaseToFile(purchaseId);
//        String attachment = purchaseId + ".json";
//
//        MimeMessage mimeMessage = emailSender.createMimeMessage();
//        MimeMessageHelper messageHelper = null;
//        try {
//            messageHelper = new MimeMessageHelper(mimeMessage, true);
//            messageHelper.setTo(toAddress);
//            messageHelper.setFrom("testforjava@yandex.ru");
//            messageHelper.setSubject(subject);
//            messageHelper.setText(message);
//            String filePath = "src/main/resources/data/" + attachment;
//            FileSystemResource fileSystemResource = new FileSystemResource(filePath);
//            messageHelper.addAttachment("Purchase Order", fileSystemResource);
//
//            emailSender.send(mimeMessage);
//
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//    }


}
