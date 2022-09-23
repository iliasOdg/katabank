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

    public void updateAccountPosition(Account account) {
        accountRepository.save(account);
    }

    public Optional<Account> getAccount(String accountNumber) {
        return accountRepository.getAccountByAccountNumber(accountNumber);
    }
}
