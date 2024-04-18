package com20.fintechapi.dto.cardDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class CardRequest {
    private String cardNumber;
    private int pinсode;
    private Long userId;

}
