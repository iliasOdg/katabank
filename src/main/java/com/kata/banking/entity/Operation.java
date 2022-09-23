package com.kata.banking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kata.banking.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(
        allowGetters = false, value = {"account"}
)
public class Operation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    @Column(name = "operation_id")
    private Integer operationId;
    @Enumerated(EnumType.STRING)
    private OperationType operationType;
    private Double operationValue;
    private Double balance;
    private LocalDateTime date = LocalDateTime.now();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    public Operation(OperationType operationType, Double operationValue, Account account) {
        this.operationType = operationType;
        this.operationValue = operationValue;
        this.account = account;
        this.balance = account.getAccountPosition();
        this.date = LocalDateTime.now();
    }
}