package com.vng.ewallet.service.card.impl;

import com.vng.ewallet.factory.card.CardCheck;
import com.vng.ewallet.factory.card.CardFactory;
import com.vng.ewallet.entity.Card;
import com.vng.ewallet.entity.CardRepository;
import com.vng.ewallet.service.card.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;

    @Override
    public List<Card> findAllCards() {
        log.info("Inside findAllCards of CardService");
        return this.cardRepository.findAll();
    }

    @Override
    public Card findCardById(Long id) {
        log.info("Inside findCardById of CardService");
        return this.cardRepository.findById(id).orElse(null);
    }

    @Override
    public Card insertCard(Card card) {
        log.info("Inside insertCard of CardService");
        checkIfCardIsValidate(card);
        return this.cardRepository.save(card);
    }

    @Override
    public void checkIfCardIsValidate(Card card) {
        log.info("Inside checkIfCardIsValidate of CardService");
        CardCheck cardCheck = CardFactory.getCardCheck(card.getCardName());
        cardCheck.check(card);
    }

    @Override
    public Card updateCard(Long id, Card card) {
        log.info("Inside updateCard of CardService");
        // Check if card is exist
        Optional<Card> optionalCard = this.cardRepository.findById(id);
        if(optionalCard.isPresent()) {
            // Check validate new card
            checkIfCardIsValidate(card);
            return this.cardRepository.save(card);
        } else {
            log.error("Inside updateCard of CardService: Card doesn't exist");
        }
        return null;
    }

    @Override
    public boolean deleteCard(Long id) {
        log.info("Inside deleteCard of CardService");
        if(this.cardRepository.existsById(id)){
            this.cardRepository.deleteById(id);
            return true;
        } else {
            log.error("Inside deleteCard of CardService: Card doesn't exist");
        }
        return false;
    }
}
