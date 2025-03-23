package com.edu.ecommerce.controllers;

import com.edu.ecommerce.dto.UserCredentials;
import com.edu.ecommerce.services.AuthService;
import com.edu.ecommerce.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<Response> login (UserCredentials credentials) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(new Response("sucess", service.login(credentials))); // cookie
    }

}
