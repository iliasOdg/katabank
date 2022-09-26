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

    /**
     * Opérations sur le compte : en fonction du montant, on détermine
     * si c'est un dépôt ou un retrait.
     *
     * @param newOperationRequest Montant et numéro du compte.
     * @return L'opération effectuée
     * @throws AccountNotFoundException: Si aucun compte correspond au numéro fourni
     * @throws InsufficientFundException Si les fonds sont insuffisants pour un retrait
     */
    @PostMapping
    ResponseEntity<Operation> executeOperation(
            @Validated @RequestBody NewOperationRequest newOperationRequest
    ) throws AccountNotFoundException, InsufficientFundException;

    /**
     * Recupère la liste des opérations sur un compte
     * trié par ordre.
     *
     * @param accountNumber numéro du compte
     * @param page          nombre de pages
     * @param size          nombre d'éléments par page
     * @return La liste des opérations
     * @throws AccountNotFoundException Si aucun compte correspond au numéro fourni
     */
    @GetMapping
    List<Operation> getAllOperationForAClient(
            @RequestParam(value = "accountNumber", required = true) String accountNumber,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size
    ) throws AccountNotFoundException;
}
