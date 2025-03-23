package com.edu.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateCustomer(
        @JsonProperty("email") String email,
        @JsonProperty("password") String password,
        @JsonProperty("name") String name,
        @JsonProperty("lastName") String lastName) {}
