package com.kata.banking.service;

import com.kata.banking.entity.Account;
import com.kata.banking.entity.Client;
import com.kata.banking.exceptions.AccountNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {

    private static final String ACCOUNT_NUMBER = "ACC01";
    @Mock
    private AccountService accountService;

    @Test
    public void should_get_account_position_for_account_number() throws AccountNotFoundException {
        Client client = Client.builder()
                .surname("James")
                .firstname("Bond")
                .email("james@email.com")
                .clientNumber(2543)
                .password("pwd")
                .role("USER")
                .build();

        Account account = Account.builder()
                .accountPosition(2000d)
                .accountNumber("ACC01")
                .client(client)
                .build();
        Optional<Account> expected = Optional.ofNullable(account);
        Mockito.when(accountService.getAccount(ACCOUNT_NUMBER)).thenReturn(expected);
        Optional<Account> result = accountService.getAccount(ACCOUNT_NUMBER);
        assertEquals(result, expected);
    }

}
