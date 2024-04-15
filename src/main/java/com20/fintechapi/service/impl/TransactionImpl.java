package com20.fintechapi.service.impl;

import com20.fintechapi.dto.SimpleResponse;
import com20.fintechapi.dto.transactionDto.TransactionRequest;
import com20.fintechapi.service.TransactionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TransactionImpl implements TransactionService {
    @Override
    public SimpleResponse createTransaction(TransactionRequest transactionRequest) {
        return null;
    }
}
