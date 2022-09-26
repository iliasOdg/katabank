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

    /**
     * Enregistrer une nouvelle opération
     *
     * @param operation opération à enregistrer
     * @param account   compte
     * @return L'opération enregistrée
     */
    @Transactional
    public Operation registerOperation(Operation operation, Account account) {
        accountService.updateAccountPosition(account);
        operation.setBalance(account.getAccountPosition());
        return operationRepository.save(operation);
    }

    /**
     * Récupère les opérations groupées par lot dans une page du compte
     *
     * @param account compte à mise à jour
     * @param page    nombre de pages
     * @param size    taille
     * @return Liste des opérations
     */
    public List<Operation> getAllOperationForAccount(Account account, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "date"));
        return operationRepository.findByAccount(account, pageRequest);
    }
}
