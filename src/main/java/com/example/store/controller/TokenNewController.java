//package com.example.store.controller;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.security.interfaces.RSAPrivateKey;
//import java.security.interfaces.RSAPublicKey;
//import java.util.Date;
//import java.util.Map;
//
//@RestController
//public class TokenNewController {
//
//    @Value("${jwt.public.key}")
//    RSAPublicKey key;
//
//    @Value("${jwt.private.key}")
//    RSAPrivateKey priv;
//
//    @PostMapping("/tokennew")
//    public String createToken(String subject, long expirationInMillis, Map<String, Object> claims) {
//        Date now = new Date();
//        Date expiration = new Date(now.getTime() + expirationInMillis);
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(subject)
//                .setIssuedAt(now)
//                .setExpiration(expiration)
//                .signWith(priv, SignatureAlgorithm.RS256)
//                .compact();
//    }
//
////    public Claims parseToken(String token) {
////        return Jwts.parser()
////                .setSigningKey(key)
////                .parseClaimsJws(token)
////                .getBody();
////    }
//
//}
