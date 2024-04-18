package com20.fintechapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Entity
@Table(name = "cards")
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Card {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "card_gen"
    )
    @SequenceGenerator(
            name = "card_gen",
            sequenceName = "card_seq",
            allocationSize = 1
    )
    private Long id;
    private String cardNumber;
    private Double balance;
    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH
    })
    private User user;
    private int pinсode;
    private ZonedDateTime dateCreation;
    private ZonedDateTime isValidUntil;

}
