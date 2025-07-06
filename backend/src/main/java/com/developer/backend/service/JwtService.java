package com.developer.backend.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.developer.backend.entity.JwtConfig;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class JwtService {

    private final JwtConfig jwtConfig;

    @Autowired
    public JwtService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }


    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtConfig.getSecretKey().getBytes());
    }

    public String generateToken(Map<String, Object> additionalClaims, String username) {
        try {
            return Jwts.builder()
                    .setClaims(additionalClaims) // Agrega las reclamaciones personalizadas
                    .setSubject(username) // Establece el sujeto (normalmente el usuario)
                    .setIssuedAt(new Date()) // Fecha de emisión
                    .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpirationTime()))
                    
                    .signWith(getSigningKey(),SignatureAlgorithm.HS256) // Firma con la clave secreta
                    .compact();
        } catch (SignatureException e) {
            // Manejo específico para errores de firma
            System.err.println("Error al firmar el token: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            // Manejo para argumentos inválidos
            System.err.println("Argumento inválido: " + e.getMessage());
        } catch (Exception e) {
            // Manejo general de excepciones
            System.err.println("Error al generar el token: " + e.getMessage());
        }
        return null; // Retorna null si ocurre un error
    }

    public Claims validateToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(jwtConfig.getSecretKey().getBytes()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            System.err.println("Error al validar el token: " + e.getMessage());
        }
        
        return null; // Retorna null si ocurre un error
    }

    private Claims  extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //este sirve para extraer algun atributo del json
    public String extractObject(String token,String object) {
        Claims claims = extractAllClaims(token);
        return claims.get(object, String.class);
    }
}

