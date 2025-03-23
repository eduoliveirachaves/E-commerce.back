package com.edu.ecommerce.configs;

import com.edu.ecommerce.filters.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    
    public static final String[] PUBLIC_ROUTES = {
            "/user",
            "/auth"
    };
    
    private final JwtAuthenticationFilter filter;
    
    public WebSecurityConfig (@Lazy JwtAuthenticationFilter filter) {
        this.filter = filter;
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http,
                                                    CorsConfigurationSource corsConfigurationSource) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                   .cors(cors -> cors.configurationSource(corsConfigurationSource))
                   .authorizeHttpRequests(auth -> auth.requestMatchers(PUBLIC_ROUTES)
                                                      .permitAll()
                                                      .requestMatchers("/api/admin/**")
                                                      .hasRole("ADMIN")
                                                      .anyRequest()
                                                      .permitAll())
                   .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                   //add mt filter implementation
                   .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                   .build();
        
        
    }
    
    // CORS configuration - Allow frontend to access the API - CORS == Cross-Origin Resource Sharing - Allows requests from different origins
    @Bean
    public CorsConfigurationSource corsConfigurationSource () {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(
                List.of("http://localhost:5173", "http://127.0.0.1:5173")); // Allow frontend - development
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        configuration.setExposedHeaders(List.of("Authorization"));
        configuration.setAllowCredentials(true); // Allow cookies
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Apply to all routes
        return source;
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}
