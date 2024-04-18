package com20.fintechapi.repository;

import com20.fintechapi.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    @Query("SELECT c FROM Card c WHERE c.cardNumber = :cardNumber")
    Card findCardByCardNumber(@Param("cardNumber") String cardNumber);
}