package com.kata.banking.service;


import com.kata.banking.entity.Account;
import com.kata.banking.entity.Operation;

import java.util.List;

public interface OperationService {

    Operation registerOperation(Operation operation, Account account);

    List<Operation> getAllOperationForAccount(Account account, int page, int size);
}
