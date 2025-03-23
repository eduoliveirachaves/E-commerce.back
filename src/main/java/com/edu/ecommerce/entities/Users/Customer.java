package com.edu.ecommerce.entities.Users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;

@Getter
@Entity
public class Customer extends User {
    
    @Column(nullable = false)
    private String lastName;
    
    private String cpf;
    
    public Customer () {
    
    }
    
    public Customer (String name, String lastName, String email, String password) {
        super(name, email, password);
        this.lastName = lastName;
    }
    
    public void setCPF (String CPF) {
        //add real validation
        
        if (cpf.isBlank() || cpf.length() < 11) {
            this.cpf = cpf;
        }
    }
    
    public String getFullName () {
        return super.getName() + " " + this.lastName;
    }
    
    
}


