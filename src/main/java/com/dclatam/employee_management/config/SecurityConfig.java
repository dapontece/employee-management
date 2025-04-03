package com.dclatam.employee_management.config;

import com.dclatam.employee_management.filter.CorsFilter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Security configuration for the application.
 * Defines authentication and authorization for the endpoints.
 */

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig implements WebMvcConfigurer {

    @Value("${controller.base-path}")
    private String basePath;

    @Bean
    @SneakyThrows
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http
                .addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class)
                .csrf(AbstractHttpConfigurer::disable) // Deshabilita CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/index.html", "/employees.html", "/settings.html", "/assets/**", "/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers(basePath + "/employees").permitAll()
                        .requestMatchers(basePath + "/employees/{id}").permitAll()
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}
