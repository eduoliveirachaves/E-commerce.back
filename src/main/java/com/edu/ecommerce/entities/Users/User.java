package com.edu.ecommerce.entities.Users;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name= "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String password;
    
    public User () {
    }
    
    public User (String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    @Override
    public String toString () {
        return "User{" + "id=" + id + ", email=" + email + ", password=" + password + '}';
    }
}
