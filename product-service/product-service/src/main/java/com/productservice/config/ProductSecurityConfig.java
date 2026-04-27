package com.productservice.config;

import com.productservice.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class ProductSecurityConfig {

    @Autowired
    private JwtFilter filter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(req -> {
                    req.requestMatchers("/api/v1/product/list/**").permitAll()
                    .requestMatchers("/api/v1/product/add").hasRole("STORE")
                            .anyRequest().authenticated();
                })
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
