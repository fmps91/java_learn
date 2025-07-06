package com.developer.backend.controller;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.backend.entity.ApiResponse;
import com.developer.backend.entity.Login;
import com.developer.backend.entity.Person;
import com.developer.backend.service.JwtService;
import com.developer.backend.service.PersonService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth Controller", description = "Operations related to authenticathion")
public class AuthController {
	
	@Autowired
	private PersonService personService;
	private JwtService jwtService;

	public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

	@PostMapping("/singnin")
	@Operation(summary = "Auth singnin", description = "Retrieve a user authenticated")
	public  ResponseEntity<ApiResponse<String>> Singnin(@RequestBody Login example) {
		try {
			Person person=personService.login(example.getUsername(),example.getPassword());
			Map<String, Object> claims = new HashMap<>();
			claims.put("role", "admin");
			claims.put("email", "johndoe@example.com");
			claims.put("department", "IT"); 
			String jwt=jwtService.generateToken(claims, person.getUsername());
			return ResponseEntity.status(200).body(new ApiResponse<>("success", jwt, "Session retrieved successfully"));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(400).body(new ApiResponse<>("error", null,e.getLocalizedMessage()));

		}
		/* 
		//System.out.println("username: "+example);
		Person person=personService.login(example.getUsername(),example.getPassword());
		
		Map<String, Object> claims = new HashMap<>();
		claims.put("role", "admin");
        claims.put("email", "johndoe@example.com");
        claims.put("department", "IT"); 
		Map<String, Object> token = new HashMap<>();
		String jwt=jwtService.generateToken(claims, person.getUsername());
		//token.put("person", person);
		//token.put("token", jwt);
		//token.put("rol", jwtService.extractObject(jwt, "role"));
		
		return token; */
	}
	
	@PostMapping("/singnup")
	@Operation(summary = "Auth singnnup", description = "Retrieve a user save in database")
	public ResponseEntity<ApiResponse<Person>> save(@RequestBody Person person) {
		
		try {
			Person obj=personService.savePerson(person);
			obj.setCreatedDate(null);
			return ResponseEntity.status(200).body(new ApiResponse<>("success", null, "Person register successfully"));

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(400).body(new ApiResponse<>("error", null,e.getLocalizedMessage()));

		}
	}
	
	
}
