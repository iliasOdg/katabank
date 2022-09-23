package com.kata.banking.service.impl;

import com.kata.banking.entity.Account;
import com.kata.banking.entity.Operation;
import com.kata.banking.repository.OperationReporitory;
import com.kata.banking.service.AccountService;
import com.kata.banking.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OperationServiceImpl implements OperationService {

    @Autowired
    private OperationReporitory operationRepository;

    @Autowired
    private AccountService accountService;

    @Transactional
    public Operation registerOperation(Operation operation, Account account) {
        accountService.updateAccountPosition(account);
        operation.setBalance(account.getAccountPosition());
        return operationRepository.save(operation);
    }

    public List<Operation> getAllOperationForAccount(Account account, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "date"));
        return operationRepository.findByAccount(account, pageRequest);
    }
}
