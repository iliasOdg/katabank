package com.kata.banking.exceptions;

public class AccountNotFoundException extends Exception {
    public AccountNotFoundException(String accountNumber) {
        super("Account number ACCNO has not been found.".replace("ACCNO", accountNumber));
    }
}
