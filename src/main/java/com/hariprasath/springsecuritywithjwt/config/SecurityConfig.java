package com.hariprasath.springsecuritywithjwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    // To turn off all the default settings
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(Customizer -> Customizer.disable()) // This will disable the CSRF default security
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/v1/register", "/v1/login")
                        .permitAll()
                        .anyRequest().authenticated()) // This will authenticate any request
                .httpBasic(Customizer.withDefaults()) // This will allow request from postman and other sources
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // This will make http request as stateless
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) //Here we are adding JWTFilter before Username and pwd filter
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

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}
