package com.pizza.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
            		.requestMatchers("/api/admins/login").permitAll()
            		.requestMatchers("/h2-console/**").permitAll()
                .anyRequest().permitAll() 
            )
            .csrf(csrf -> csrf.disable())
        	.headers(headers -> headers.frameOptions().sameOrigin());
        return http.build();
    }
    
//    @Bean
//    public org.springframework.security.crypto.password.PasswordEncoder passwordEncoder() {
//        return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
//    }
    
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	    http
//	        .authorizeHttpRequests(auth -> auth
//	            .requestMatchers("/admin/login", "/h2-console/**").permitAll() // allow login and H2 console
//	            .requestMatchers("/admin/dashboard","/admin/profile","/admin/branch/**").authenticated() // protect all other admin pages
//	            .anyRequest().permitAll()
//	        )
//	   
//	        .logout(logout -> logout
//	            .logoutUrl("/admin/logout")
//	            .logoutSuccessUrl("/admin/login?logout")
//	            .invalidateHttpSession(true)
//	            .deleteCookies("JSESSIONID")
//	        )
//	        .csrf(csrf -> csrf.disable())
//	        .headers(headers -> headers.frameOptions().sameOrigin());
//-----------------------------------------------------------
//		http
//	    .authorizeHttpRequests(auth -> auth
//	        .requestMatchers(HttpMethod.GET, "/admin/login").permitAll()
//	        .requestMatchers(HttpMethod.POST, "/admin/login").permitAll()
//	        .requestMatchers("/admin/dashboard","/admin/profile","/admin/branch/**").authenticated()
//	        .anyRequest().permitAll()
//	    )
//	    
//	    .formLogin(form -> form.disable())
//	    
//	    .logout(logout -> logout
//	        .logoutUrl("/admin/logout")
//	        .logoutSuccessUrl("/admin/login?logout")
//	        .invalidateHttpSession(true)
//	        .deleteCookies("JSESSIONID")
//	    )
//	    .csrf(csrf -> csrf.disable())
//	    .headers(headers -> headers.frameOptions().sameOrigin());
//
//		
//	    return http.build();
//	}


}
