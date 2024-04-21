package com20.fintechapi.service;

import com20.fintechapi.dto.SimpleResponse;
import com20.fintechapi.dto.transactionDto.TransactionRequest;
import com20.fintechapi.dto.transactionDto.TransactionResponse;
import com20.fintechapi.dto.transactionDto.UserTransactionRequest;

import java.util.List;

public interface TransactionService {
    SimpleResponse createTransaction(TransactionRequest transactionRequest);

    List<TransactionResponse> getAll();

    TransactionResponse getById(Long transactionId);


    SimpleResponse deleteById(Long transactionId);


}
