package com.bingle.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class JwtUtil {

    @Value("${jwt.secret}")
    private static String SECRET_KEY;
    private static final String BEARER = "Bearer ";
    private static final Duration ACCESS_TOKEN_EXPIRATION = Duration.ofHours(6);
    private static final Duration REFRESH_TOKEN_EXPIRATION = Duration.ofDays(60);

    public static String generateAccessToken(String subject) {
        return generateToken(subject, ACCESS_TOKEN_EXPIRATION);
    }

    public static String generateRefreshToken(String subject) {
        return generateToken(subject, REFRESH_TOKEN_EXPIRATION);
    }

    public static String generateToken(String subject, Duration expiration) {
        Instant now = Instant.now();
        Date issuedAt = Date.from(now);
        Date expirationDate = Date.from(now.plus(expiration));

        SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(issuedAt)
                .setExpiration(expirationDate)
                .signWith(secretKey)
                .compact();
    }

    public static Claims parseToken(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isValidToken(String token) {
        if (!isBearerScheme(token)) {
            return false;
        }

        return true;
    }

    public static boolean isBearerScheme(String token) {
        if (token.startsWith(BEARER)) {
            return true;
        }

        return false;
    }
}
