package com.academy.flightsystem.api.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String key;

    @Value("${jwt.expiration}")
    private Long expiration;

    public void generateToken(String username) {

    }

    public boolean isTokenValid(String token) {
        return true;
    }
}
