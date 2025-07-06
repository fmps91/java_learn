package com.developer.backend;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.developer.backend.util.JwtTokenUtil;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		/* JwtTokenUtil jwtUtil = new JwtTokenUtil();

        // Reclamos personalizados
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", "admin");
        claims.put("email", "johndoe@example.com");
        claims.put("department", "IT");

        // Generar el token
        String token = jwtUtil.generateToken(claims,"jona");

        System.out.println("Generated Token: " + token);
 		*/


		 /* JwtTokenUtil jwtUtil = new JwtTokenUtil();

		 // Ejemplo de token generado previamente
		 String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiYWRtaW4iLCJkZXBhcnRtZW50IjoiSVQiLCJlbWFpbCI6ImpvaG5kb2VAZXhhbXBsZS5jb20iLCJzdWIiOiJqb25hIiwiaWF0IjoxNzM0NTc3NjAzLCJleHAiOjE3MzQ1ODEyMDN9.ijVrJFNX-2191fjfLy4UYhDIIPPzs3Fawdeax1p4eVU";
 
		 // Decodificar el token
		 Map<String, Object> claims = jwtUtil.decodeToken(token);
		
		 //imprimir todos los atributos del token
		 // Imprimir las reclamaciones
		 claims.forEach((key, value) -> System.out.println(key + ": " + value)); */
		
		/* JwtTokenUtil jwtUtil = new JwtTokenUtil();

		String token="eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiYWRtaW4iLCJkZXBhcnRtZW50IjoiSVQiLCJlbWFpbCI6ImpvaG5kb2VAZXhhbXBsZS5jb20iLCJzdWIiOiJqb25hIiwiaWF0IjoxNzM0NTc3NjAzLCJleHAiOjE3MzQ1ODEyMDN9.ijVrJFNX-2191fjfLy4UYhDIIPPzs3Fawdeax1p4eVU";

		String user=jwtUtil.extractUsername(token);

		System.out.println("token: "+user); */


		SpringApplication.run(BackendApplication.class, args);
	}

}
