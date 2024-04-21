package com20.fintechapi.api;

import com20.fintechapi.dto.SimpleResponse;
import com20.fintechapi.dto.cardDto.CardRequest;
import com20.fintechapi.dto.transactionDto.TransactionRequest;
import com20.fintechapi.dto.transactionDto.TransactionResponse;
import com20.fintechapi.dto.transactionDto.UserTransactionRequest;
import com20.fintechapi.repository.CardRepository;
import com20.fintechapi.repository.dao.CardDao;
import com20.fintechapi.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transaction/")
@Tag(name = "transaction API")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TransactionApi {
    private final TransactionService transactionService;

    @PostMapping("/postTransaction")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "to create new transaction")
    public SimpleResponse createTransaction(@RequestBody TransactionRequest transactionRequest) {
        return transactionService.createTransaction(transactionRequest);
    }

    @GetMapping("/getAllTransactions")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "to get all transaction (ADMIN can do)")
    public List<TransactionResponse> getAll() {
        return transactionService.getAll();
    }

    @GetMapping("/getTransactionById")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "to get transaction by id (only ADMIN can do)")
    public TransactionResponse getById(@RequestParam Long transactionId) {
        return transactionService.getById(transactionId);
    }

    @DeleteMapping("/deleteTransaction")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "to delete transaction by id (only ADMIN can do)")
    public SimpleResponse deleteById(@RequestParam Long transactionId) {
        return transactionService.deleteById(transactionId);
    }


}
