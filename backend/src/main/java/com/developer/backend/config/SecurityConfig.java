package com.developer.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.developer.backend.util.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,JwtDecoder jwtDecoder) throws Exception {
       
     
        http
            .cors(cors -> cors.configurationSource(request -> {
                var corsConfiguration = new org.springframework.web.cors.CorsConfiguration();
                corsConfiguration.setAllowedOrigins(java.util.List.of("http://localhost:4200"));
                corsConfiguration.setAllowedMethods(java.util.List.of("GET", "POST", "PUT", "DELETE"));
                corsConfiguration.setAllowedHeaders(java.util.List.of("*"));
                corsConfiguration.setAllowCredentials(true);
                return corsConfiguration;
            }))
            .csrf(csrf -> csrf.disable()) // Deshabilita CSRF si no es necesario
            
            .authorizeHttpRequests(auth -> auth
                //.requestMatchers("/api/auth/**").permitAll() // Rutas públicas
                .requestMatchers("/**").permitAll()
                
                //.requestMatchers("/v1/authenticate","/v3/api-docs/**","/swagger-ui/**","/swagger-ui.html").permitAll()
                //.requestMatchers("/v1/authenticate","/v3/api-docs/**","/swagger-ui/**","/swagger-ui.html").authenticated()
                //.anyRequest().authenticated() // Rutas protegidas
            )
            /* .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            ) */
            .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class) // Añade tu filtro personalizado
            .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.decoder(jwtDecoder)));
           

        return http.build();
    }
}
