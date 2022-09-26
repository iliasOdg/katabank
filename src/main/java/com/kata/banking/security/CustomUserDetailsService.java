package com.kata.banking.security;

import com.kata.banking.entity.Client;
import com.kata.banking.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    /**
     * Récupère les informations de l'utilisateur à partir de
     * son username ou email fourni
     *
     * @param usernameOrEmail username ou surname
     * @return UserDetails
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail) {

        Optional<Client> client = Optional.of(clientRepository.findBySurnameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail)
                ));

        return UserPrincipal.create(client.get());
    }

    /**
     * Utilisé dans la JwtAuthFilter
     *
     * @param id Identifiant de l'utilisateur
     * @return UserDetails
     */
    @Transactional
    public UserDetails loadUserById(Integer id) {

        Client user = clientRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );

        return UserPrincipal.create(user);
    }
}
