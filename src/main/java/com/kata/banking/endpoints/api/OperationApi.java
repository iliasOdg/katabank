package com.kata.banking.endpoints.api;

import com.kata.banking.endpoints.model.NewOperationRequest;
import com.kata.banking.entity.Operation;
import com.kata.banking.exceptions.AccountNotFoundException;
import com.kata.banking.exceptions.InsufficientFundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/operations")
public interface OperationApi {
    @PostMapping
    ResponseEntity<Operation> executeOperation(
            @Validated @RequestBody NewOperationRequest newOperationRequest
    ) throws AccountNotFoundException, InsufficientFundException;

    @GetMapping
    List<Operation> getAllOperationForAClient(
            @RequestParam(value = "accountNumber", required = true) String accountNumber,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size
    ) throws AccountNotFoundException;
}
