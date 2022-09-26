package com.kata.banking.endpoints.controller;

import com.kata.banking.endpoints.api.AuthApi;
import com.kata.banking.endpoints.model.AuthRequest;
import com.kata.banking.endpoints.model.AuthResponse;
import com.kata.banking.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthController implements AuthApi {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider tokenProvider;

    /**
     * Génère un jeton d'authentification pour accéder
     * aux requêtes sécurisées
     *
     * @param loginRequest Identifiants de connexion
     * @return ResponseEntity<AuthResponse> : Jeton
     */
    @Override
    public ResponseEntity<AuthResponse> authenticateUser(AuthRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new AuthResponse(jwt));
    }

}
