package com.edu.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = "com.edu")
@EntityScan(basePackages = "com.edu.ecommerce.entities")
@EnableJpaRepositories("com.edu.ecommerce.repositories")
public class ECommerceApplication {
    
    public static void main (String[] args) {
        SpringApplication.run(ECommerceApplication.class, args);
    }
    
}
