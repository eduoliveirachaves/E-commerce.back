package com.edu.ecommerce.services;

import com.edu.ecommerce.dto.UserCredentials;
import com.edu.ecommerce.entities.Users.User;
import com.edu.ecommerce.utils.CustomCookie;
import jakarta.servlet.http.Cookie;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * AuthService class.
 * This class is used to authenticate users.
 * It provides methods to login and logout users.
 * It uses the UserService and JwtService classes.
 * Used by the AuthController.
 */

@Service
public class AuthService {
    
    private final UserService userService;
    
    private final JwtService jwtService;
    
    public AuthService (@Lazy UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }
    
    public Cookie login (UserCredentials userCredentials) {
        User user = userService.getUserByEmail(userCredentials.email())
                               .orElseThrow(
                                       () -> new RuntimeException("User does not exist: " + userCredentials.email()));
        
        if (!jwtService.matches(userCredentials.password(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        
        String token = jwtService.createToken(user.getId());
        
        return CustomCookie.create(token);
    }
    
    public Cookie logout () {
        return CustomCookie.delete();
    }
}
