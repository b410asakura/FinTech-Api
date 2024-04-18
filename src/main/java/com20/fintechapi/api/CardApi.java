package com20.fintechapi.api;

import com20.fintechapi.dto.SimpleResponse;
import com20.fintechapi.dto.cardDto.BalanceRequest;
import com20.fintechapi.dto.cardDto.CardRequest;
import com20.fintechapi.dto.cardDto.CardResponse;
import com20.fintechapi.entity.Card;
import com20.fintechapi.globalException.NotFoundException;
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
    @Operation(summary = "to get all cards")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public List<CardResponse> getAll() {
        return cardService.getAll();
    }

    @GetMapping("/getCardById")
    @Operation(summary = "to get card by id")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public CardResponse getById(@RequestParam Long id) {
        Card card = cardRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("there is no card with id = %s ", id))
        );
        return CardResponse.builder()
                .id(card.getId())
                .cardNumber(card.getCardNumber())
                .balance(card.getBalance())
                .userId(card.getUser().getId())
                .userFullName(card.getUser().getFirstName() + " " + card.getUser().getLastName())
                .build();
    }

    @PostMapping
    @Operation(summary = "to create card")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public SimpleResponse createCard(@RequestBody CardRequest cardRequest) {
        return cardService.createCard(cardRequest);
    }

    @PutMapping
    @Operation(summary = "to change card balance")
    public SimpleResponse changeCardBalance(@RequestBody BalanceRequest balanceRequest, @RequestParam Long id) {
        return cardService.changeBalance(id, balanceRequest);
    }

    @DeleteMapping
    @Operation(summary = "to delete card")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public SimpleResponse deleteCard(@RequestParam Long id) {
        return cardService.deleteCard(id);
    }

}
