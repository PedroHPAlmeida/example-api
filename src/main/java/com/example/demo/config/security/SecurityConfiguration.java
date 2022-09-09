package com.example.demo.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/clientes").permitAll()
                .antMatchers(HttpMethod.GET, "/api/clientes/*").permitAll()
                .antMatchers(HttpMethod.POST, "/api/clientes").permitAll()
                .anyRequest().authenticated();
        http.headers().frameOptions().sameOrigin();
        return http.build();
    }
}
