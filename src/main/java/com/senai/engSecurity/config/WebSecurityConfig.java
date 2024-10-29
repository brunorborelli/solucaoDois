package com.senai.engSecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private static final String BASE_API_PATH = "/api";

    @Autowired
    private UserDetailsService customUserDetailsService;

    @Autowired
    public void configureGlobalAuthentication(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
        authManagerBuilder
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Bean
    public SecurityFilterChain configureSecurity(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(BASE_API_PATH + "/").permitAll() // Public route
                        .requestMatchers(HttpMethod.POST, BASE_API_PATH + "/register").permitAll() // Registration route
                        .requestMatchers(BASE_API_PATH + "/user/dashboard").hasRole("USER") // User dashboard route
                        .requestMatchers(BASE_API_PATH + "/admin/console").hasRole("ADMIN") // Admin exclusive route
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}