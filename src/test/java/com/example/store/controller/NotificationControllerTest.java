package com.example.store.controller;


import com.example.store.config.JwtTokenProvider;
import com.example.store.dto.notification.NotificationDtoRequest;
import com.example.store.service.notification.NotificationService;
import com.example.store.service.notification.NotificationServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

//@Import(NotificationTestConfig.class)
//@TestPropertySource(locations="classpath:application-test.properties")
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

//    @Qualifier("getNotificationService")
    @MockBean
    private NotificationService notificationService;

    @Autowired
    private JwtEncoder jwtEncoder;


    @Test
    public void testSendSimpleEmail() throws Exception {
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(jwtEncoder);

        // Генерируем токен
        String token = jwtTokenProvider.generateToken("testuser", "ROLE_USER");
        // Prepare request body
        NotificationDtoRequest request = new NotificationDtoRequest();
        request.setRecipientId(UUID.randomUUID());
        request.setHeader("Test Email");
        request.setText("This is a test email");

        doNothing().when(notificationService).createSimpleEmail(any(NotificationDtoRequest.class));

        // Perform the request
        mockMvc.perform(MockMvcRequestBuilders.post("/store/notification/simple-email")
                        .content(asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Verify service interaction
        // Here you can add assertions or verifications based on your service mocks
    }

    // Utility method to convert object to JSON string
    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}



