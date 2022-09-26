package com.kata.banking.service;


import com.kata.banking.entity.Account;
import com.kata.banking.entity.Operation;

import java.util.List;

public interface OperationService {

    /**
     * Enregistrer une nouvelle opération
     *
     * @param operation opération à enregistrer
     * @param account   compte
     * @return L'opération enregistrée
     */
    Operation registerOperation(Operation operation, Account account);

    /**
     * Récupère les opérations groupées par lot dans une page du compte
     *
     * @param account compte à mise à jour
     * @param page    nombre de pages
     * @param size    taille
     * @return Liste des opérations
     */
    List<Operation> getAllOperationForAccount(Account account, int page, int size);
}
