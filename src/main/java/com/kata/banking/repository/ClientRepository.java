package com.kata.banking.repository;

import com.kata.banking.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findBySurnameOrEmail(String usernameOrEmail, String usernameOrEmail1);
}
