package com20.fintechapi.repository.dao;

import com20.fintechapi.dto.transactionDto.TransactionResponse;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDao {

    List<TransactionResponse> getAll();

    List<TransactionResponse> getAllTransactions();
}
