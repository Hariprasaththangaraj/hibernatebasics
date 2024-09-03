package com.hariprasath.springsecuritywithjwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    // To turn off all the default settings
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(Customizer -> Customizer.disable()) // This will disable the CSRF default security
                .authorizeHttpRequests(request -> request.anyRequest().authenticated()) // This will authenticate any request
                .httpBasic(Customizer.withDefaults()) // This will allow request from postman and other sources
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // This will make http request as stateless
                .build();
    }


    //We used to authenticate http requests and DAO is used to authenticate from DB
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(); // Create a DaoAuthenticationProvider instance
        // Use BCryptPasswordEncoder for password encoding/decoding
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12)); // Set the password encoder with BCrypt algorithm
        provider.setUserDetailsService(userDetailsService); // Set the UserDetailsService to retrieve user details
        return provider; // Return the configured AuthenticationProvider
    }



}
