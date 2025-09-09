package com.pizza.user.config;

import com.pizza.user.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtUtil {

    private final String secretKey = "MySuperSecretKeyForJWTGeneration12345"; // same secret key for all services
    private final long expirationMs = 1000 * 60 * 60; // 1 hour

    // ============ GENERATE TOKEN ============
    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(String.valueOf(user.getUserId())) // store userId
                .claim("email", user.getEmail())
                .claim("role", "USER")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
                .compact();
    }

    // ============ PARSE TOKEN ============
    public Long getUserIdFromToken(String token) {
        return Long.parseLong(
            Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token)
                .getBody()
                .getSubject()
        );
    }
}
