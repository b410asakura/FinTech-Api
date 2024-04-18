package com20.fintechapi.api;

import com20.fintechapi.dto.SimpleResponse;
import com20.fintechapi.dto.transactionDto.TransactionRequest;
import com20.fintechapi.dto.transactionDto.TransactionResponse;
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
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @Operation(summary = "to create new transaction")
    public SimpleResponse createTransaction(@RequestBody TransactionRequest transactionRequest) {
        return transactionService.createTransaction(transactionRequest);
    }

    @GetMapping("/getAllTransactionsByUserId")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @Operation(summary = "to get all transaction")
    public List<TransactionResponse> getAllByUserId(@RequestParam Long userId) {
        return transactionService.getAllByUserId(userId);
    }

    @GetMapping("/getTransactionById")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @Operation(summary = "to get transaction by id")
    public TransactionResponse getById(@RequestParam Long transactionId) {
        return transactionService.getById(transactionId);
    }

    @PutMapping("/updateTransaction")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "to update transaction by id (not working because i think transaction can't be updated")
    public SimpleResponse updateById(@RequestParam Long transactionId) {
        return transactionService.updateById(transactionId);
    }

    @DeleteMapping("/deleteTransaction")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "to delete transaction by id")
    public SimpleResponse deleteById(@RequestParam Long transactionId) {
        return transactionService.deleteById(transactionId);
    }
}
