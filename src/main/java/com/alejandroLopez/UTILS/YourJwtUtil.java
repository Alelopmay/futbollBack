package com.alejandroLopez.UTILS;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;

public class YourJwtUtil {
    private static final long EXPIRATION_TIME = 3600000; // 1 hora en milisegundos
    private String jwtSecret;

    public YourJwtUtil(String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    public String generateToken(Long workerId, String category) {
        byte[] decodedKey = Base64.getDecoder().decode(this.jwtSecret);
        SecretKey secretKey = Keys.hmacShaKeyFor(decodedKey);

        Map<String, Object> claims = new HashMap<>();
        claims.put("category", category);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(workerId.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(Base64.getDecoder().decode(this.jwtSecret)).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getWorkerIdFromToken(String token) {
        return Long.parseLong(Jwts.parserBuilder()
                .setSigningKey(Base64.getDecoder().decode(this.jwtSecret))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject());
    }

    public String getCategoryFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Base64.getDecoder().decode(this.jwtSecret))
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("category", String.class);
    }
}
