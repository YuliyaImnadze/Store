//package com.example.store.config;
//
//
//import com.example.store.dto.notification.NotificationDtoRequest;
//import com.example.store.service.notification.NotificationService;
//import com.example.store.service.notification.NotificationServiceImpl;
//import org.mockito.Mockito;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Profile;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.doNothing;
//
//@TestConfiguration
//@Profile("test")
//public class NotificationTestConfig {
//
//    @Bean
//    public NotificationServiceImpl getNotificationService()  {
//        NotificationServiceImpl notificationService =  Mockito.mock(NotificationServiceImpl.class);
//        doNothing().when(notificationService).createSimpleEmail(any(NotificationDtoRequest.class));
//        return notificationService;
//    }
//
//}
