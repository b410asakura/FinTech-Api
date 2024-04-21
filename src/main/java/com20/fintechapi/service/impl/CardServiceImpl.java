package com20.fintechapi.service.impl;

import com20.fintechapi.dto.SimpleResponse;
import com20.fintechapi.dto.cardDto.BalanceRequest;
import com20.fintechapi.dto.cardDto.CardRequest;
import com20.fintechapi.dto.cardDto.CardResponse;
import com20.fintechapi.entity.Card;
import com20.fintechapi.entity.User;
import com20.fintechapi.globalException.NotFoundException;
import com20.fintechapi.repository.CardRepository;
import com20.fintechapi.repository.UserRepository;
import com20.fintechapi.repository.dao.CardDao;
import com20.fintechapi.service.CardService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardDao cardDao;
    private final CardRepository cardRepository;
    private final UserRepository userRepository;

    @Override
    public List<CardResponse> getAll() {
        return cardDao.getAll();
    }

    @Override
    public SimpleResponse createCard(CardRequest cardRequest) {
        User user = userRepository.findById(cardRequest.getUserId()).orElseThrow(()
                -> new NotFoundException(String.format("user not found with id = %s", cardRequest.getUserId())));
        Card card = new Card();
        card.setCardNumber(cardRequest.getCardNumber());
        card.setBalance(0.00);
        card.setDateCreation(ZonedDateTime.now());
        card.setPinсode(cardRequest.getPinсode());
        card.setIsValidUntil(ZonedDateTime.now().plusYears(10));
        card.setUser(user);
        cardRepository.save(card);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("card successfully saved")
                .build();

    }

    @Override
    public SimpleResponse changeBalance(Long id, BalanceRequest balanceRequest) {
        Card card = cardRepository.findById(id).orElseThrow(()
                -> new NotFoundException(String.format("there is no card with id = %s", id)));
        card.setBalance(balanceRequest.getBalance());
        cardRepository.save(card);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("balance successfully changed")
                .build();
    }

    @Override
    public SimpleResponse deleteCard(Long id) {
        cardRepository.findById(id).orElseThrow(()
                -> new NotFoundException(String.format("there is no card with id = %s", id)));
        cardRepository.deleteById(id);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("card successfully deleted")
                .build();
    }

    @Override
    public CardResponse getById(Long id) {
        Card card = cardRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("there is no card with id = %s ", id))
        );
        return CardResponse.builder()
                .id(card.getId())
                .cardNumber(card.getCardNumber())
                .balance(card.getBalance())
                .userId(card.getUser().getId())
                .userFullName(card.getUser().getFirstName() + " " + card.getUser().getLastName())
                .build();
    }

    @Override
    public List<CardResponse> showAllCards(Long userId) {
        return cardDao.showAllCards(userId);
    }
}
