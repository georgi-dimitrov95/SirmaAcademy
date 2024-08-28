package com.academy.flightsystem.api.security;

import com.academy.flightsystem.api.filters.JwtAuthenticationFilter;
import com.academy.flightsystem.api.model.UserInfo;
import com.academy.flightsystem.api.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserInfoService();
    }



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers("/auth/**", "/").permitAll()
                                .requestMatchers("/auth/admin").hasRole("ADMIN")
                                .requestMatchers("/auth/user").hasRole("USER")
                                .anyRequest().authenticated() // Requires authentication for all other requests
                );
        return http.build();
    }
}
