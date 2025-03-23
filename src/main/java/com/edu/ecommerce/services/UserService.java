package com.edu.ecommerce.services;

import com.edu.ecommerce.dto.CreateCustomer;
import com.edu.ecommerce.entities.Users.Customer;
import com.edu.ecommerce.entities.Users.User;
import com.edu.ecommerce.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserService {
    
    private final UserRepository repository;
    
    private final JwtService jwt;
    
    public UserService (UserRepository repository, JwtService jwt) {
        this.jwt = jwt;
        this.repository = repository;
    }
    
    public Optional<User> getUserByEmail (String email) {
        return repository.findByEmail(email);
    }
    
    public User createCustomer (CreateCustomer dto) {
        String password = jwt.getHashedPassword(dto.password());
        
        log.info("password: {}", password);
        
        User user = new Customer(dto.name(), dto.lastName(), dto.email(), password);
        
        repository.save(user);
        
        log.info("User exists: {}", getUserByEmail(user.getEmail()));
        
        return user;
    }
}
