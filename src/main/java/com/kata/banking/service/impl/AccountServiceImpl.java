package com.kata.banking.service.impl;

import com.kata.banking.entity.Account;
import com.kata.banking.repository.AccountRepository;
import com.kata.banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    /**
     * Mise à jour du solde d'un compte
     *
     * @param account compte mis à jour
     */
    public void updateAccountPosition(Account account) {
        accountRepository.save(account);
    }

    /**
     * Récupère les infos d'un compte
     *
     * @param accountNumber Numero du compte
     * @return Optional<Account>
     */
    public Optional<Account> getAccount(String accountNumber) {
        return accountRepository.getAccountByAccountNumber(accountNumber);
    }
}
