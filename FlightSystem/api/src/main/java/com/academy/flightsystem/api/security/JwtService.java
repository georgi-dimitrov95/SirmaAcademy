package com.academy.flightsystem.api.security;

import io.jsonwebtoken.Claims;
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

//    pretty much the same as buildToken method, should be refactored or deleted
//    check 10:23 on java brains jwt video - the methods there have the same problem...
//    imo this method should be called createCustomClaims(UserInfoDetails user)-> create a new hashmap, populate it with custom claims, return the hashmap; then use the output(hashmap) of that method into Jwts.builder.setClaims(output hashmap) in buildToken method
    public String generateToken(String username) {
        return buildToken(username);
    }

    public String buildToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + getExpiration()))
                .signWith(getSignKey())
                .compact();
    }

    private Claims getAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isTokenValid(String token) {
        return true;
    }

}
