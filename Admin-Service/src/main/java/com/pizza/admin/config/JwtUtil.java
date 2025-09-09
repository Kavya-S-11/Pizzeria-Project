package com.pizza.admin.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import com.pizza.admin.entity.Admin;

import java.util.Date;

@Service
public class JwtUtil {

	private final String secretKey = "MySuperSecretKeyForJWTGeneration12345"; // 36 chars

    private final long expirationMs = 1000 * 60 * 60; // 1 hour

    // ================= GENERATE TOKEN =================
    public String generateToken(Admin admin) {
        return Jwts.builder()
                .setSubject(String.valueOf(admin.getId())) // store adminId
                .claim("username", admin.getUsername())
                .claim("role", admin.getAdminRole().name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
                .compact();
    }

    // ================= PARSE TOKEN =================
    public Long getAdminIdFromToken(String token) {
        return Long.parseLong(
            Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token)
                .getBody()
                .getSubject()
        );
    }
}
