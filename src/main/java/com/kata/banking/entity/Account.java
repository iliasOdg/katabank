package com.kata.banking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    @Column(name = "account_id")
    private Integer accountId;
    @Column(unique = true, nullable = false)
    private String accountNumber;
    @Column(nullable = false)
    private Double accountPosition;
    @ManyToOne
    private Client client;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "account",
            cascade = CascadeType.MERGE)
    private List<Operation> operations;

    public Account(String accountNumber, Double accountPosition, Client client) {
        this.accountNumber = accountNumber;
        this.accountPosition = accountPosition;
        this.client = client;
    }

}
