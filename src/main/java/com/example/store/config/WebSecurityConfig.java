package com.example.store.config;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
@Slf4j
@EnableWebSecurity
public class WebSecurityConfig {

    @Value("${jwt.public.key}")
    RSAPublicKey key;

    @Value("${jwt.private.key}")
    RSAPrivateKey priv;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable) //   http.csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((requests) -> requests
                                .requestMatchers(HttpMethod.POST, "/store/user/create").permitAll() // регистрация пользователя
                                .requestMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/token").authenticated()
                                .requestMatchers(HttpMethod.POST, "/store/product/create").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/store/company").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/store/user").hasAuthority("ROLE_ADMIN")
                                .anyRequest().authenticated()
//                                .anyRequest().permitAll()
                )
                .httpBasic(Customizer.withDefaults())
                .oauth2ResourceServer((oauth2) -> {
                    log.info("JWT Resource Server configuration is being applied.");
                    oauth2.jwt(Customizer.withDefaults());
                })
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()))
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(customizer -> customizer
                                .decoder(jwtDecoder())
                        ))
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling((exceptions) -> exceptions
                        .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
                        .accessDeniedHandler(new BearerTokenAccessDeniedHandler())
                        .defaultAuthenticationEntryPointFor(
                                (request, response, authException) -> {
                                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                                    response.getWriter().write("Custom unauthorized message");
                                },
                                new AntPathRequestMatcher("/**") // Обработчик по умолчанию для всех путей
                        )
                );
        return http.build();
    }


    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(this.key).build();
    }


    @Bean
    JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(this.key).privateKey(this.priv).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }


    @Bean
    public PasswordEncoder userPasswordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

}
