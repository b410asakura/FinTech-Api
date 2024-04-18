package com20.fintechapi.dto.cardDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class CardResponse {
    private Long id;
    private String cardNumber;
    private Double balance;
    private Long userId;
    private String userFullName;

    public CardResponse() {

    }
}
