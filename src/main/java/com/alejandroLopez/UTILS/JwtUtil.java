package com.alejandroLopez.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "MI_CLAVE_SECRETA_SUPER_SEGURA"; // Debe ser segura y larga

    public String generateToken(String username, String category) {
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        return Jwts.builder()
                .setSubject(username)
                .claim("category", category) // Agregamos la categoría del usuario
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // Expira en 1 hora
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
