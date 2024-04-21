package com20.fintechapi.service.impl;

import com20.fintechapi.dto.SimpleResponse;
import com20.fintechapi.dto.transactionDto.TransactionRequest;
import com20.fintechapi.dto.transactionDto.TransactionResponse;
import com20.fintechapi.dto.transactionDto.UserTransactionRequest;
import com20.fintechapi.entity.Card;
import com20.fintechapi.entity.Transaction;
import com20.fintechapi.enums.TransactionType;
import com20.fintechapi.globalException.NotFoundException;
import com20.fintechapi.repository.CardRepository;
import com20.fintechapi.repository.TransactionRepository;
import com20.fintechapi.repository.dao.TransactionDao;
import com20.fintechapi.service.TransactionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final CardRepository cardRepository;
    private final TransactionDao transactionDao;

    @Override
    public SimpleResponse createTransaction(TransactionRequest transactionRequest) {
        Card sourceCard = cardRepository.findById(transactionRequest.getSourceCardId()).orElseThrow(()
                -> new NotFoundException(String.format("there no card with id = %s ", transactionRequest.getSourceCardId())));
        Card destinationCard = cardRepository.findById(transactionRequest.getDestinationCardId()).orElseThrow(()
                -> new NotFoundException(String.format("there no card with id = %s ", transactionRequest.getSourceCardId())));
        if (sourceCard.getBalance() >= transactionRequest.getAmount()) {
            sourceCard.setBalance(sourceCard.getBalance() - transactionRequest.getAmount());
            destinationCard.setBalance(destinationCard.getBalance() + transactionRequest.getAmount());
            cardRepository.save(sourceCard);
            cardRepository.save(destinationCard);
        } else {
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message("Not enough funds on your balance")
                    .build();
        }
        Transaction transaction = Transaction.builder()
                .amount(transactionRequest.getAmount())
                .sourceCard(sourceCard)
                .destinationCard(destinationCard)
                .currency(transactionRequest.getCurrency())
                .dateTime(ZonedDateTime.now())
                .transactionType(TransactionType.TRANSFER)
                .build();
        transactionRepository.save(transaction);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("transaction has been successfully made")
                .build();
    }

    @Override
    public List<TransactionResponse> getAll() {
        return transactionDao.getAll();
    }

    @Override
    public TransactionResponse getById(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId).orElseThrow(()
                -> new NotFoundException(String.format("There is no transaction with id = %s", transactionId)));
        String sourceCard = cardRepository.findById(transaction.getSourceCard().getId()).orElseThrow(()
                -> new NotFoundException(String.format("There is no card with id = %s", transaction.getSourceCard().getId()))).getCardNumber();
        String destinationCard = cardRepository.findById(transaction.getDestinationCard().getId()).orElseThrow(()
                -> new NotFoundException(String.format("There is no card with id = %s", transaction.getDestinationCard().getId()))).getCardNumber();
        new TransactionResponse();
        return TransactionResponse.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .fee(transaction.getFee())
                .feeAmount(transaction.getFeeAmount())
                .dateTime(transaction.getDateTime())
                .sourceCard(sourceCard)
                .destinationCard(destinationCard)
                .currency(transaction.getCurrency())
                .transactionType(transaction.getTransactionType())
                .build();
    }

    @Override
    public SimpleResponse deleteById(Long transactionId) {
        transactionRepository.findById(transactionId).orElseThrow(()
                -> new NotFoundException(String.format("There is no transaction with id = %s", transactionId)));
        transactionRepository.deleteById(transactionId);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("transaction has been successfully deleted")
                .build();
    }
}
