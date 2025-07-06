package com.developer.backend.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.developer.backend.entity.JwtConfig;

import java.io.IOException;
import java.security.Key;
import java.util.Collections;
import java.util.Date;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtConfig jwtConfig;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtConfig.getSecretKey().getBytes());
    }

    //@SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = extractToken(request); // Método para extraer el token
        if (token != null && validateToken(token)) {
            Claims claims = extractAllClaims(token); // Extraer información del token
            String username = claims.getSubject();

            // Configura el contexto de seguridad
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7); // Remueve "Bearer " del token
        }
        return null;
    }

    private boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            System.out.println("error: "+e);
            return false;
        }
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

 

    public String generateToken(String username) {

        Key key = Keys.hmacShaKeyFor(jwtConfig.getSecretKey().getBytes());
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpirationTime()))
                .signWith(key)
                .compact();
    }
}

