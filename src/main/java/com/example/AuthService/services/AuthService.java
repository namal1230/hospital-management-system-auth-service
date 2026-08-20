package com.example.AuthService.services;

import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;


@Service
public class AuthService {
    // For demo only. Load from config/keystore in prod.
    private final String secret = "hgnghnnfnfgnfnfnfnfnfnnnhnfdnggnththrhtrhrjhyjujtrjtyjyhyhhrhtrhrsthhhhsththrhyrhyhyhyh";
    public String login(String username, String password) {
        // Validate credentials (call user-service or DB)
        // Here assume success for demo
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600_000))
                .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                .compact();
    }
}
