package com.pizza.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
            		.requestMatchers("/api/users/**").permitAll()
            		.requestMatchers("/h2-console/**").permitAll()
                .anyRequest().permitAll() 
            )
            .csrf(csrf -> csrf.disable())
        	.headers(headers -> headers.frameOptions().sameOrigin());
        return http.build();
    }
}
