package com.edu.ecommerce.filters;

import com.edu.ecommerce.auth.CustomUserDetailsService;
import com.edu.ecommerce.services.JwtService;
import com.edu.ecommerce.configs.WebSecurityConfig;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
    private final JwtService jwtService;
    
    private final CustomUserDetailsService userDetailsService;
    
    public JwtAuthenticationFilter (JwtService jwtService, CustomUserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }
    
    @Override
    protected void doFilterInternal (@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                     @NonNull FilterChain filterChain) throws ServletException, IOException {
        
        // If the route is public bypass authentication
        String requestPath = request.getServletPath();
        String[] publicRoutes = WebSecurityConfig.PUBLIC_ROUTES;
        for (String route : publicRoutes) {
            if (route.equals(requestPath)) {
                filterChain.doFilter(request, response);
                SecurityContextHolder.clearContext();
                return;
            }
        }
        
        // Get token from the request header
        String token = getTokenFromCookies(request);
        
        if (StringUtils.hasText(token) && jwtService.validateToken(token)) {
            Long id = jwtService.extractUserId(token);
            
            if (id != null && SecurityContextHolder.getContext()
                                                   .getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserById(id);
                
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                
                SecurityContextHolder.getContext()
                                     .setAuthentication(authentication);
            }
        }
        
        filterChain.doFilter(request, response);
    }
    
    private String getTokenFromCookies (HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName()
                          .equals("token")) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
    


