package com20.fintechapi.api;

import com20.fintechapi.dto.SimpleResponse;
import com20.fintechapi.dto.transactionDto.TransactionRequest;
import com20.fintechapi.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transaction/")
@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
@Tag(name = "transaction API")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TransactionApi {
    private final TransactionService transactionService;

    @PostMapping()
    @Operation(summary = "to create new transaction")
    public SimpleResponse createTransaction(@RequestBody TransactionRequest transactionRequest) {
        return transactionService.createTransaction(transactionRequest);
    }
}
