package com20.fintechapi.repository.dao;

import com20.fintechapi.dto.cardDto.CardResponse;
import com20.fintechapi.entity.Card;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardDao {
    Card getByCardNumber(String cardNumber);

    List<CardResponse> getAll();
}
