package com.kata.banking.repository;

import com.kata.banking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Account a SET a.accountPosition = a.accountPosition + :newOperation WHERE a.accountNumber = :accountNumber")
    void updateAccountPosition(@Param("newOperation") double newOperation, @Param("accountNumber") int accountNumber);

    Optional<Account> getAccountByAccountNumber(String accountNumber);

    @Query("Select a.accountPosition FROM Account a WHERE a.accountNumber = :accountNumber")
    Double getPositionForAccountNumber(@Param("accountNumber") String accountNumber);
}
