package com.kata.banking.service;

import com.kata.banking.entity.Account;
import com.kata.banking.entity.Client;
import com.kata.banking.entity.Operation;
import com.kata.banking.enums.OperationType;
import com.kata.banking.repository.OperationReporitory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OperationServiceTest {

    @Mock
    private OperationService operationService;

    @Mock
    private AccountService accountService;

    @Mock
    private OperationReporitory operationRepository;


    @Test
    public void register_operation_nominal() {
        Client client = Client.builder()
                .surname("James")
                .firstname("Bond")
                .email("james@email.com")
                .clientNumber(2543)
                .role("USER")
                .build();
        Account account = new Account("ACC01", 500d, client);
        Operation expectedOperation = new Operation(OperationType.DEPOSIT, 500d, account);
        Operation givenOperation = new Operation(OperationType.DEPOSIT, 500d, account);

        Mockito.when(accountService.getAccount(Mockito.any())).thenReturn(Optional.of(account));

        Mockito.when(operationRepository.save(Mockito.any()))
                .thenReturn(expectedOperation);

        Mockito.when(operationService.registerOperation(Mockito.any(), Mockito.any()))
                .thenReturn(expectedOperation);

        Operation insertedOperation = operationService.registerOperation(givenOperation, account);

        assertThat(insertedOperation, is(expectedOperation));
    }

}

