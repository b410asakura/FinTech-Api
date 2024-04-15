package com20.fintechapi.service;

import com20.fintechapi.dto.SimpleResponse;
import com20.fintechapi.dto.transactionDto.TransactionRequest;

public interface TransactionService {
    SimpleResponse createTransaction(TransactionRequest transactionRequest);
}
