package com.edu.ecommerce.controllers;

import com.edu.ecommerce.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleGeneralException (Exception e) {
        log.error("AN ERROR OCURRED: {}", e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body(new Response("An uexpected error ocurred: " + e.getMessage()));
    }
    
    
}
