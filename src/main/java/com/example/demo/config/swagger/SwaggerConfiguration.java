package com.example.demo.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Value("${info.app.version}")
    private String version;

    @Value("${springdoc.info.description}")
    private String description;

    @Value("${springdoc.info.title}")
    private String title;

    @Bean
    public OpenAPI infoApi() {
        Info info = new Info();
        info.setTitle(title);

        SecurityRequirement securityItem = new SecurityRequirement();
        securityItem.addList("Authorization");

        Components components = new Components();
        components.addSecuritySchemes("Authorization", new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT"));

        return new OpenAPI().components(components)
                .addSecurityItem(securityItem)
                .info(info.description(description).version(version));
    }
}
