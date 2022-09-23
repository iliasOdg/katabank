package com.kata.banking.repository;

import com.kata.banking.entity.Account;
import com.kata.banking.entity.Operation;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationReporitory extends JpaRepository<Operation, Integer> {
    List<Operation> findByAccount(Account account, PageRequest pageRequest);
}
