package com20.fintechapi.service;

import com20.fintechapi.dto.SimpleResponse;
import com20.fintechapi.dto.cardDto.BalanceRequest;
import com20.fintechapi.dto.cardDto.CardRequest;
import com20.fintechapi.dto.cardDto.CardResponse;

import java.util.List;

public interface CardService {
    List<CardResponse> getAll();

    SimpleResponse createCard(CardRequest cardRequest);

    SimpleResponse changeBalance(Long id, BalanceRequest balanceRequest);

    SimpleResponse deleteCard(Long id);

}
