package com.kata.banking.endpoints.api;

import com.kata.banking.endpoints.model.AuthRequest;
import com.kata.banking.endpoints.model.AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public interface AuthApi {

    /**
     * Génère un jeton d'authentification pour accéder
     * aux requêtes sécurisées
     *
     * @param authRequest Identifiants de connexion
     * @return ResponseEntity<AuthResponse> : Jeton
     */
    @PostMapping("/generate-token")
    ResponseEntity<AuthResponse> authenticateUser(@Valid @RequestBody AuthRequest authRequest);
}
