package com.kata.banking.exceptions;

public class InsufficientFundException extends Exception {
    public InsufficientFundException(String accountNumber) {
        super(String.format("operation refused, insufficient fund for %s.", accountNumber));
    }
}