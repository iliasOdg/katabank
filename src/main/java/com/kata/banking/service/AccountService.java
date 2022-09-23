package com.kata.banking.service;

import com.kata.banking.entity.Account;

import java.util.Optional;

public interface AccountService {
    void updateAccountPosition(Account account);

    Optional<Account> getAccount(String accountNumber);
}
