package com.edu.ecommerce.controllers;

import com.edu.ecommerce.dto.UserCredentials;
import com.edu.ecommerce.services.AuthService;
import com.edu.ecommerce.utils.Response;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/auth")
@RestController
public class AuthController {
    
    private final AuthService service;
    
    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Response> login (@RequestBody UserCredentials credentials, HttpServletResponse response) {
        response.addCookie(service.login(credentials));
        return ResponseEntity.status(HttpStatus.OK)
                             .body(new Response("sucess"));
    }

}
