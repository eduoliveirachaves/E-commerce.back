package com.edu.ecommerce.controllers;

import com.edu.ecommerce.annotations.CurrentUser;
import com.edu.ecommerce.dto.CreateCustomer;
import com.edu.ecommerce.entities.Users.User;
import com.edu.ecommerce.services.UserService;
import com.edu.ecommerce.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/users")
@RestController
public class UserController {
    
    private final UserService service;
    
    public UserController (UserService userService) {
        this.service = userService;
    }
    
    @GetMapping("/test")
    public ResponseEntity<Response> test () {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(new Response("ITS WORKING"));
    }
    
    @PostMapping
    public ResponseEntity<Response> createCustomer (@RequestBody CreateCustomer customer) {
        log.info("RECEBENDO REQUEST");
        log.info("THIS IS THE OBJECT: {}", customer);
        
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(new Response("User created sucessfully", service.createCustomer(customer)));
    }
    
    @GetMapping
    public ResponseEntity<Response> getUser (@CurrentUser User user) {
        return ResponseEntity.ok(new Response("ALL WORKING", user));
    }
    
    
}
