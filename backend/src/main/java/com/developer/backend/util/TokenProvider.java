package com.developer.backend.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class TokenProvider {

    private final String SECRET_KEY = "mySecretKey123456789012345678901234567890"; // Usa una clave segura
    private final long EXPIRATION_TIME = 86400000; // 1 día en milisegundos

    public String generateToken(String username) {
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            // Convierte SECRET_KEY en una instancia Key
            Key signingKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

            // Valida el token usando Jwts.parserBuilder()
            Jwts.parserBuilder()
                .setSigningKey(signingKey) // Configura la clave de firma
                .build()
                .parseClaimsJws(token); // Analiza y valida el token

            return true;
        } catch (Exception e) {
            // Si ocurre cualquier excepción, el token no es válido
            return false;
        }
    }

    public String extractObject(String token,String object) {
        Claims claims = extractAllClaims(token);
        return claims.get(object, String.class);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Key getSigningKey() {
        // Convierte SECRET_KEY en una instancia Key válida
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        // Extrae las reclamaciones (claims) del token
        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey()) // Configura la clave de firma
                .build()
                .parseClaimsJws(token) // Analiza y valida el token
                .getBody();
        return claimsResolver.apply(claims);
    }
}
