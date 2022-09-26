package com.kata.banking.service;

import com.kata.banking.entity.Account;

import java.util.Optional;

public interface AccountService {
    /**
     * Mise à jour du solde d'un compte
     *
     * @param account compte mis à jour
     */
    void updateAccountPosition(Account account);

    /**
     * Récupère les infos d'un compte
     *
     * @param accountNumber Numero du compte
     * @return Optional<Account>
     */
    Optional<Account> getAccount(String accountNumber);
}
