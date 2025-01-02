package com.example.store.config;

import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class JwtTokenProvider {

    private final JwtEncoder jwtEncoder;

    public JwtTokenProvider(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public String generateToken(String username, String role) {
        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("example-store") // Укажите ваш issuer
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS)) // Токен будет действителен 1 час
                .subject(username)
                .claim("scope", role) // Добавляем роль как claim
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}

