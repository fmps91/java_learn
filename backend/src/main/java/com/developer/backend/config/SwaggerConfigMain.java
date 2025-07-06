package com.developer.backend.config;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.Components;

import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT"
)
public class SwaggerConfigMain {


        @Bean
        public OpenAPI customOpenAPI() {
            Server devServer= new Server();
            devServer.setUrl("http://localhost:5000");
            devServer.setDescription("Server url in dev enviroment:");

            Server prodServer= new Server();
            prodServer.setUrl("https://algunserver.com");
            prodServer.setDescription("Server url in prod enviroment:");

            Contact contact= new Contact();
            contact.setEmail("algunserver@gmail.com");
            contact.setName("algunserver");
            contact.setUrl("https://algunserver.com");
            
            License mitLicense= new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

            Info info=new Info()
            .title("Maneja de la api de un ecommerse")
            .version("1.0.0")
            .contact(contact)
            .description("The API exposes endpoints  to manage tutorials.")
            .termsOfService("alguna url")
            .license(mitLicense);

            
           /*  SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name("Authorization");

            SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearerAuth"); */

            return new OpenAPI()
                    .info(info)
                    .servers(List.of(devServer,prodServer));
                    /* .components(new Components().addSecuritySchemes("bearerAuth", securityScheme)) // Agrega el esquema de seguridad
                    .addSecurityItem(securityRequirement); */
                    
        }
}
    
