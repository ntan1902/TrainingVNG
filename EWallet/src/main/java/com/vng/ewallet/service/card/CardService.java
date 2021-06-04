package com.vng.ewallet.service.card;

import com.vng.ewallet.entity.Card;

import java.util.List;

public interface CardService {
    List<Card> findAllCards();

    Card findCardById(Long id);

    Card insertCard(Card card);

    void checkIfCardIsValidate(Card card);

    Card updateCard(Long id, Card card);

    boolean deleteCard(Long id);
}
