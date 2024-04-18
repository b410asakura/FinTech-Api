package com20.fintechapi.service;

import com20.fintechapi.dto.SimpleResponse;
import com20.fintechapi.dto.transactionDto.TransactionRequest;
import com20.fintechapi.dto.transactionDto.TransactionResponse;

import java.util.List;

public interface TransactionService {
    SimpleResponse createTransaction(TransactionRequest transactionRequest);

    List<TransactionResponse> getAllByUserId(Long userId);

    TransactionResponse getById(Long transactionId);

    SimpleResponse updateById(Long transactionId);

    SimpleResponse deleteById(Long transactionId);
}
