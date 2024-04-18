package com20.fintechapi.entity;

import com20.fintechapi.enums.Currency;
import com20.fintechapi.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Entity
@Table(name = "transactions")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "transaction_gen"
    )
    @SequenceGenerator(
            name = "transaction_gen",
            sequenceName = "transaction_seq",
            allocationSize = 1
    )
    private Long id;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    private ZonedDateTime dateTime;
    @ManyToOne( cascade = {
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.REFRESH
            })
    private Card sourceCard;
    @ManyToOne( cascade = {
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH
    })
    private Card destinationCard;
    private int fee;
    private Double feeAmount;
    @Enumerated(EnumType.STRING)
    private Currency currency;
}
