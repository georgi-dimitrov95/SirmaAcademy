package com.academy.flightsystem.api.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

// JwtService is responsible for generating, validating and extracting info from JWTs.
// It handles core operations like creating & extracting claims and validating tokens against user details.
@Service
@Getter
public class JwtService {

    @Value("${jwt.secret}")
    private String key;

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

    public Claims getAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractUsername(String token) {
        String username = extractClaim(token,Claims::getSubject);
        return username;
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername());
    }
}
