package com20.fintechapi.dto.transactionDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserTransactionRequest {
    private Double amount;
    private String destinationCard;
}
