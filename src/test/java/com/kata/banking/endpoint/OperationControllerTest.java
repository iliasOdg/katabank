package com.kata.banking.endpoint;

import com.kata.banking.BankingApplication;
import com.kata.banking.endpoints.controller.OperationController;
import com.kata.banking.entity.Account;
import com.kata.banking.entity.Client;
import com.kata.banking.entity.Operation;
import com.kata.banking.enums.OperationType;
import com.kata.banking.exceptions.AccountNotFoundException;
import com.kata.banking.service.AccountService;
import com.kata.banking.service.OperationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BankingApplication.class})
public class OperationControllerTest {

    private static final String ACCOUNT_NUMBER = "ACC01";
    @Mock
    OperationController operationController;
    @Mock
    ModelMapper modelMapper;
    @Mock
    private OperationService operationService;
    @Mock
    private AccountService accountService;

    @Test
    public void get_all_operations_test_ok_with_null_auth() throws AccountNotFoundException {
        Client client = Client.builder()
                .surname("James")
                .firstname("Bond")
                .email("james@email.com")
                .clientNumber(2543)
                .password("pwd")
                .role("USER")
                .build();

        Account account = new Account(ACCOUNT_NUMBER, 500d, client);
        Operation operation = new Operation(OperationType.DEPOSIT, 50d, account);

        when(accountService.getAccount(ACCOUNT_NUMBER))
                .thenReturn(java.util.Optional.of(account));
        when(operationService.getAllOperationForAccount(Mockito.any(), Mockito.anyByte(), Mockito.anyByte()))
                .thenReturn(List.of(operation));

        List<Operation> response = operationController.getAllOperationForAClient(ACCOUNT_NUMBER, 0, 10);
        Assert.assertEquals(Collections.emptyList(), response);
    }

}
