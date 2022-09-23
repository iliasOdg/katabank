package com.kata.banking.endpoints.controller;

import com.kata.banking.endpoints.api.OperationApi;
import com.kata.banking.endpoints.model.NewOperationRequest;
import com.kata.banking.entity.Account;
import com.kata.banking.entity.Operation;
import com.kata.banking.enums.OperationType;
import com.kata.banking.exceptions.AccountNotFoundException;
import com.kata.banking.exceptions.InsufficientFundException;
import com.kata.banking.service.AccountService;
import com.kata.banking.service.OperationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OperationController implements OperationApi {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    OperationService operationService;

    @Autowired
    private AccountService accountService;

    @Override
    public ResponseEntity<Operation> executeOperation(NewOperationRequest newOperationRequest) throws AccountNotFoundException, InsufficientFundException {
        Operation operation = modelMapper.map(newOperationRequest, Operation.class);
        operation.setOperationType(newOperationRequest.getOperationValue() > 0 ? OperationType.DEPOSIT : OperationType.WITHDRAW);

        Optional<Account> optionalAccount = accountService.getAccount(newOperationRequest.getAccountNumber());
        Account account = optionalAccount.orElseThrow(() -> new AccountNotFoundException(newOperationRequest.getAccountNumber()));

        double newAccountPosition = account.getAccountPosition() + newOperationRequest.getOperationValue();
        if (newAccountPosition < 0) {
            throw new InsufficientFundException(String.valueOf(newOperationRequest.getAccountNumber()));
        }
        account.setAccountPosition(newAccountPosition);
        operation.setAccount(account);
        operation = operationService.registerOperation(operation, account);
        return ResponseEntity.ok(operation);
    }


    @Override
    public List<Operation> getAllOperationForAClient(String accountNumber, int page, int size) throws AccountNotFoundException {
        Optional<Account> optionalAccount = accountService.getAccount(accountNumber);
        if (optionalAccount.isEmpty()) {
            throw new AccountNotFoundException(String.valueOf(accountNumber));
        }
        Account account = optionalAccount.get();
        return operationService.getAllOperationForAccount(account, page, size);
    }
}
