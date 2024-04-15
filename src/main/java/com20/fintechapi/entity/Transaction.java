package com20.fintechapi.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transactions")
@Data
@Builder
@NoArgsConstructor
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
    private String context;
}
