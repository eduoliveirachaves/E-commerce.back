package com.edu.ecommerce.entities.Users;

import jakarta.persistence.Entity;

@Entity
public class Seller extends User {
    
    private String CNPJ;
    
    private String CompanyName;
}
