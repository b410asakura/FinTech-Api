package com20.fintechapi.dto.transactionDto;

import com20.fintechapi.enums.Currency;
import com20.fintechapi.enums.TransactionType;
import lombok.*;

import java.time.ZonedDateTime;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {
    private Long id;
    private Double amount;
    private int fee;
    private Double feeAmount;
    private Currency currency;
    private String sourceCard;
    private String destinationCard;
    private TransactionType transactionType;
    private ZonedDateTime dateTime;


}
