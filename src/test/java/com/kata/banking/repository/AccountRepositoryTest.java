package com.kata.banking.repository;

import com.kata.banking.BankingApplication;
import com.kata.banking.entity.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BankingApplication.class})
public class AccountRepositoryTest {

    private static final String ACCOUNT_NUMBER = "ACC01";
    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void position_not_null_for_a_known_account_number() {
        assertThat(accountRepository.getPositionForAccountNumber(ACCOUNT_NUMBER)).isEqualTo(2000d);
    }

    @Test
    public void position_is_null_for_an_unknown_account() {
        assertThat(accountRepository.getPositionForAccountNumber("UNKNOWN")).isNull();
    }

    @Test
    public void should_return_a_known_account() {
        Optional<Account> result = accountRepository.getAccountByAccountNumber(ACCOUNT_NUMBER);
        assertThat(result).isPresent();
    }
}
