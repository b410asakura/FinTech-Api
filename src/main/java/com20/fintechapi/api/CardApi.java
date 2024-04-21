package com20.fintechapi.api;

import com20.fintechapi.dto.SimpleResponse;
import com20.fintechapi.dto.cardDto.BalanceRequest;
import com20.fintechapi.dto.cardDto.CardRequest;
import com20.fintechapi.dto.cardDto.CardResponse;
import com20.fintechapi.repository.CardRepository;
import com20.fintechapi.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/card")
@Tag(name = "Card API")
public class CardApi {
    private final CardService cardService;
    private final CardRepository cardRepository;

    @GetMapping("/getAllCards")
    @Operation(summary = "to get all cards (only ADMIN can do)")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public List<CardResponse> getAll() {
        return cardService.getAll();
    }

    @GetMapping("/getCardById")
    @Operation(summary = "to get card information by id (only ADMIN can do)")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public CardResponse getById(@RequestParam Long id) {
        return cardService.getById(id);
    }

    @GetMapping("/showAllMyCards")
    @Operation(summary = "to show all one user's cards (ADMIN and USER can do ")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<CardResponse> showAllCards(@RequestParam Long userId) {
        return cardService.showAllCards(userId);
    }


    @PostMapping
    @Operation(summary = "to create card (only ADMIN can do)")
    @PreAuthorize("hasAnyAuthority('ADMIN')")

    public SimpleResponse createCard(@RequestBody CardRequest cardRequest) {
        return cardService.createCard(cardRequest);
    }

    @PutMapping
    @Operation(summary = "to change card balance (ADMIN and USER can do)")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public SimpleResponse changeCardBalance(@RequestBody BalanceRequest balanceRequest, @RequestParam Long id) {
        return cardService.changeBalance(id, balanceRequest);
    }

    @DeleteMapping
    @Operation(summary = "to delete card (only ADMIN can do)")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public SimpleResponse deleteCard(@RequestParam Long id) {
        return cardService.deleteCard(id);
    }

}
