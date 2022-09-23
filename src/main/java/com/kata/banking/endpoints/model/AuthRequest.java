package com.kata.banking.endpoints.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class AuthRequest {
    @NotBlank
    private String usernameOrEmail = "James";

    @NotBlank
    private String password = "Password";
}
