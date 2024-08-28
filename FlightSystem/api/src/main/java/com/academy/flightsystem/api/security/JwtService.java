package com.academy.flightsystem.api.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

// JwtService is responsible for generating, validating and extracting info from JWTs.
// It handles core operations like creating & extracting claims and validating tokens against user details.
@Service
public class JwtService {

    @Getter
    @Value("${jwt.secret}")
    private String key;

    @Getter
    @Value("${jwt.expiration}")
    private Long expiration;

    private SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(getKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String username) {
        return "";
    }

    public boolean isTokenValid(String token) {
        return true;
    }

}
