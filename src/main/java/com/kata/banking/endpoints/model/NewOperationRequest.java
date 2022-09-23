package com.kata.banking.endpoints.model;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public final class NewOperationRequest {
    @NotNull
    private String accountNumber;
    @NotNull
    private Double operationValue;
}
