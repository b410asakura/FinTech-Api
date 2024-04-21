package com20.fintechapi.dto.transactionDto;

import com20.fintechapi.dto.cardDto.CardRequest;
import com20.fintechapi.enums.Currency;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TransactionRequest {
    private Double amount;
    private Long sourceCardId;
    private Long destinationCardId;
    private Currency currency;
}
