package com.developer.backend.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public class JwtTokenUtil {

    private static final String SECRET_KEY = "Mysecret-1234567890-secretkey032"; // Al menos 256 bits (32 caracteres)
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hora

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(Map<String, Object> additionalClaims, String username) {
        try {
            return Jwts.builder()
                    .setClaims(additionalClaims) // Agrega las reclamaciones personalizadas
                    .setSubject(username) // Establece el sujeto (normalmente el usuario)
                    .setIssuedAt(new Date()) // Fecha de emisión
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Fecha de expiración
                    .signWith(getSigningKey(), SignatureAlgorithm.HS256) // Firma con la clave secreta
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

    public Claims decodeToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            // Manejo de errores: token inválido o expirado
            System.out.println("Error al decodificar el token: " + e.getMessage());
            return null;
        }
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

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        // Extrae las reclamaciones (claims) del token
        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey()) // Configura la clave de firma
                .build()
                .parseClaimsJws(token) // Analiza y valida el token
                .getBody();
        return claimsResolver.apply(claims);
    }


    //solo extrae el usuario del token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //este sirve para extraer algun atributo del json
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
}
